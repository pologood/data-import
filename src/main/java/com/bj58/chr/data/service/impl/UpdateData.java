package com.bj58.chr.data.service.impl;

import java.net.URLDecoder;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.ConstantUtils;
import com.bj58.chr.data.esb.CvOperTypeEnum;
import com.bj58.chr.data.esb.MessageSend;
import com.bj58.chr.data.esb.message.CvPostMessage;
import com.bj58.chr.data.handler.ICVContentHandler;
import com.bj58.chr.data.handler.SyncHandler;
import com.bj58.chr.data.handler.SyncHandler.UserStatusEnum;
import com.bj58.chr.data.model.CVContent;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.ICvStrategyService;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.service.IUpdateData;
import com.bj58.chr.data.utils.JSONUtils;
import com.bj58.chr.data.utils.QDStringUtils;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 
 * @author minds
 * @version 1.0
 * @date 2016年5月5日 下午8:06:34
 * @see
 * @since
 */
@Service
public class UpdateData extends FileReadService<CVContent> implements IUpdateData {

	private final static Logger LOG = LoggerFactory.getLogger(UpdateData.class);
	private final String COLLECTION = "cvInfoBo";

	@Resource
	private ICVContentHandler cvContentHandler;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Resource
	private ICvIdCoIdService cvIdCoIdServcie;

	@Resource(name = "cvMessageSend")
	private MessageSend cvMessageSend;

	@Resource(name = "syncCvHandler")
	private SyncHandler<CvInfoBo> syncCvHandler;

	@Resource
	private ICvStrategyService cvStrategyService;

	@Resource(name = "cvsMongoTemplate")
	private MongoTemplate ycMongoTemplate;

	@Resource
	private ISeekerUserService seekerUserService;

	private final static AtomicInteger count = new AtomicInteger();

	@Override
	public void handlerFile(CVContent t) {
		update(t);
	}

	/**
	 * @param cvcontent
	 */
	private void update(CVContent cvcontent) {

		String debase64;
		try {
			debase64 = URLDecoder.decode(cvcontent.getContent(), ConstantUtils.CHARASET);
			debase64 = new String(Base64.decodeBase64(debase64.replaceAll("\\s", "+")), ConstantUtils.CHARASET);
			CVModel cvModel = JSONUtils.readValue(debase64, CVModel.class);
			if (cvModel != null) {
				CvInfoBo cvInfoBo = cvContentHandler.build(cvModel);
				CvIdCoId cvCo = new CvIdCoId(cvcontent.getCoid());
				CvIdCoId cvidCoid = cvIdCoIdServcie.findById(cvCo);
				if (cvidCoid != null) {
					LOG.info("1========" + cvidCoid.toString());
					CvInfoBo source = cvInfoBoService.findById(cvidCoid.getCvId());
					if (source != null) {
						cvInfoBo.setId(source.getId());
						cvInfoBo.setCnName(source.getCnName());
						cvInfoBo.setEmail(source.getEmail());
						cvInfoBo.setMobile(source.getMobile());
						cvInfoBo.setUid(source.getUid());
						saveForYc(cvInfoBo);
						count.incrementAndGet();
					}
				}
			}
			if (count.get() % 100 == 0) {
				LOG.info("update count is :" + count.get());
			}
		} catch (Exception e) {
			LOG.error("", e);
		}
	}

	public void saveForYc(final CvInfoBo bo) {
		SeekerUser user = seekerUserService.findById(new SeekerUser(bo.getUid(), "", ""));
		bo.setInfoId(user == null ? "" : user.getCoId());
		bo.setVisibility("partly");
		bo.setVerified("1");
		bo.setIsCompleted("1");
		bo.setSrc(10L);
		setWorkTime(bo);
		if (null == bo.getLiving() || StringUtils.isEmpty(bo.getLiving().getCityId())
				|| StringUtils.isEmpty(bo.getMobile()) || getCvInFoBo(bo)) {
			LOG.info(String.format("cvisempty cvid is %s", bo.getId()));
			return;
		}
		bo.setInfoId(user == null ? "" : user.getCoId());
		bo.setVisibility("partly");
		bo.setVerified("1");
		bo.setIsCompleted("1");
		bo.setSrc(10L);
		setDefaultTime(bo);
		/** 同步离线库 */
		cvInfoBoService.saveOrUpdate(bo, bo.getInfoId());
		syncCvForYc(bo);
	}

	public void syncCvForYc(CvInfoBo bo) {
		UserStatusEnum status = syncCvHandler.getUserStatus(bo);
		if (status == null)
			return;
		/** 没有找到相同手机号 */
		if (status.getCode() == UserStatusEnum.NULL.getCode()) {
			/** 绑定新用户 */
			cvStrategyService.bundUser(bo);
		}
		/** 用户已过期 解绑老用户.新增用户,简历 */
		else if (status.getCode() == UserStatusEnum.ALLXP.getCode()) {
			cvStrategyService.unBundUser(bo, status.getUid());
			/** 老账号新增简历 */
		} else {
			if (null != syncCvHandler.update(bo)) {
				bo.setUid(status.getUid());
				ycMongoTemplate.save(bo, COLLECTION);
				LOG.info(String.format("addcv uid is %s cvid is %s", bo.getUid(), bo.getId()));
				/**
				 * 简历索引发送更新请求
				 */
				cvMessageSend.send(new CvPostMessage(bo.getId(), String.valueOf(bo.getUid()), JSONUtils.writeValue(bo),
						"update", CvOperTypeEnum.UPDATECONTENT.getId(), ""));
			}
		}
	}

	/**
	 * 设置默认时间
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月27日
	 * @param bo
	 *            void
	 */
	private void setDefaultTime(CvInfoBo bo) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH, -1);
		if (bo.getAddTime() <= 0) {
			bo.setAddTime(QDStringUtils.getCvTime(ca.getTimeInMillis()));
		}
		if (bo.getLModTime() <= 0) {
			bo.setLModTime(QDStringUtils.getCvTime(ca.getTimeInMillis()));
		}
		if (bo.getLRefTime() <= 0) {
			bo.setLRefTime(QDStringUtils.getCvTime(ca.getTimeInMillis()));
		}
	}

	/**
	 * 根据工作时间算出参加工作时间
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月28日
	 * @param bo
	 * @return long
	 */
	private void setWorkTime(CvInfoBo bo) {
		if (bo.getWorkTime() <= 0) {
			if (!CollectionUtils.isEmpty(bo.getExperience())) {
				long workTime = bo.getExperience().get(bo.getExperience().size() - 1).getStart();
				bo.setWorkTime(workTime);
				bo.setIsWork(1);
			}
		} else {
			bo.setIsWork(1);
		}
	}

	/**
	 * 获取简历实体
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月29日
	 * @param id
	 * @return String
	 */
	private boolean getCvInFoBo(CvInfoBo bo) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(bo.getId()));
		boolean flag = ycMongoTemplate.exists(query, COLLECTION);
		if (flag) {
			ycMongoTemplate.save(bo, COLLECTION);
			/**
			 * 简历索引发送更新请求
			 */
			cvMessageSend.send(new CvPostMessage(bo.getId(), String.valueOf(bo.getUid()), JSONUtils.writeValue(bo),
					"update", CvOperTypeEnum.UPDATECONTENT.getId(), ""));
		}
		return flag;
	}

}
