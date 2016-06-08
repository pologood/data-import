package com.bj58.chr.data.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.dao.ICvInfoBoDao;
import com.bj58.chr.data.esb.MessageSend;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.model.CVContract.Data;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.service.ISeekersCallBackService;
import com.bj58.chr.data.service.IStatService;
import com.google.common.collect.Maps;

/**
 * 
 * 
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午7:04:06
 * @see
 * @since
 */
@Service
public class CvInfoBoService implements ICvInfoBoService {

	private Logger logger = Logger.getLogger(CvInfoBoService.class);

	// private ICVService cvService = ProxyFactory.create(ICVService.class,
	// "tcp://cvservice/CVServiceImpl");

	@Resource
	private ICvInfoBoDao cvInfoBoDao;

	@Resource
	private ICvIdCoIdService cvIdCoIdServcie;

	@Resource
	private ISeekersCallBackService seekersCallBackService;

	@Resource
	private ISeekerUserService seekerUserService;

	@Resource(name = "cvMessageSend")
	private MessageSend cvMessageSend;

	@Resource
	private IContractService contractService;

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Resource
	private IStatService statService;

	@Override
	public CvInfoBo findById(String cvId) {
		return cvInfoBoDao.findById(cvId);
	}

	@Override
	public Serializable save(CvInfoBo t, CvIdCoId cvCo) {
		if (t == null || cvCo == null || StringUtils.isEmpty(cvCo.getCoId())) {
			throw new NullPointerException("params cvInfoBo is null or CvIdCoId is null or coId is empty");
		}
		Serializable uid = null;
		boolean contract = true;
		SeekerUser user = seekerUserService.findByCoId(cvCo.getCoId());
		if (user != null) {// 新增一份简历(英才自有用户)
			uid = user.getUid();
			Map<String, String> map = seekerUserService.getUserInfo(String.valueOf(uid));
			if (map != null) {
				cvCo.setYc(true);
				t.setMobile(map.get("mobile"));
				t.setEmail(map.get("email"));
				CVContract cVContract = new CVContract();
				cVContract.setCoid(cvCo.getCoId());
				Data data = new Data();
				data.setEmails(t.getEmail());
				data.setNamemobiles(t.getCnName() + "," + t.getMobile());
				cVContract.setData(data);
				cVContract.setCreatedTime(new Date());
				cVContract.setUpdateTime(new Date());
				cVContract.setStatus(2);
				cVContract.setUid(String.valueOf(uid));
				contractService.save(cVContract);
				// 更新屏蔽词
				// try {
				// cvService.addOrUpdateFilterWords(String.valueOf(uid), cvId,
				// t.getExperience() != null ?
				// t.getExperience().get(0).getComName()
				// : "",
				// Visibility.PARTLY.getId(), "");
				// } catch (Exception e) {
				// logger.error("CvInfoBoService.save", e);
				// }
			}
		} else {
			uid = seekersCallBackService.createSeekers(cvCo);// 创建用户
			cvCo.setYc(false);
			contract = false;
			statService.importNew();
		}
		if (uid != null) {// 创建用户，没有要联系方式
			t.setUid(String.valueOf(uid));
			t.setInfoId(cvCo.getCoId());
			t.setVisibility("partly");
			t.setVerified("1");
			t.setIsCompleted("1");
			String cvId = (String) cvInfoBoDao.save(t);
			cvCo.setCvId(cvId);
			cvIdCoIdServcie.save(cvCo);
			// 获得联系方式
			if (!contract) {
				asyncTaskService.updateContract(cvCo.getCoId());
			}
			logger.info(JSONUtils.writeValue(t));
			return cvId;
		} else {
			logger.error(String.format("cuser create fail.cvInfoBo is %s", JSONUtils.writeValue(t)));
			return null;
		}
	}

	@Override
	public Serializable saveOrUpdate(CvInfoBo cvInfoBo, String coid) {
		CvIdCoId cvCo = new CvIdCoId(coid);
		CvIdCoId cvidCoid = cvIdCoIdServcie.findById(cvCo);
		/** 不存在合作id-cvid 关系 新增简历 */
		if (cvidCoid == null || StringUtils.isEmpty(cvidCoid.getCvId())) {// 新增简历或者用户更新简历
			save(cvInfoBo, cvCo);
			return cvCo.getCvId();
		} else {// 历史推送
			SeekerUser user = seekerUserService.findByCoId(coid);
			cvInfoBo.setUid(user == null ? "" : user.getUid());
			cvInfoBo.setId(cvidCoid.getCvId());
			cvInfoBo.setInfoId(user.getCoId());
			Map<String, String> map = seekerUserService.getUserInfo(String.valueOf(user.getUid()));
			if (map != null) {
				cvInfoBo.setEmail(map.get("email"));
				cvInfoBo.setMobile(map.get("mobile"));
			}
			statService.importOld();
			return cvInfoBoDao.saveOrUpdate(cvInfoBo);
		}
	}

	@Override
	public void updateByField(Map<String, Object> params, String coid) {
		CvIdCoId cvidCoid = cvIdCoIdServcie.findById(new CvIdCoId(coid));
		if (cvidCoid != null && !StringUtils.isEmpty(cvidCoid.getCvId())) {
			cvInfoBoDao.updateByField(cvidCoid.getCvId(), params);
		} else {
			logger.error(String.format("updateForMap error,not found cvid. coid is %s and params is %s", coid,
					JSONUtils.writeValue(params)));
		}
	}

	@Override
	public void updateForContract(String mobile, String email, String name, String coid) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("cnName", name);
		params.put("email", email);
		params.put("mobile", mobile);
		updateByField(params, coid);
	}

	@Override
	public int getCvCountForUid(String uid) {
		return cvInfoBoDao.getCvCountForUid(uid);
	}

	@Override
	public List<String> getCvidsForUid(String uid) {
		return cvInfoBoDao.getCvidsForUid(uid);
	}

	@Override
	public int getCvCountByTime(String key, long date) {
		return cvInfoBoDao.getCvCountByTime(key, date);
	}

	@Override
	public List<CvInfoBo> getCvCountByTimePageList(String key, long date, Direction sort, int skip, int limit) {
		return cvInfoBoDao.getCvCountByTimePageList(key, date, sort, skip, limit);
	}

}
