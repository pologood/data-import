package com.bj58.chr.data.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.CVencryptCheckEnum;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.IRuleService;
import com.bj58.chr.data.utils.QDencrypt;
import com.google.common.collect.Sets;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午6:23:25
 * @see
 * @since
 */
public abstract class AbstractHandler implements Handler {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractHandler.class);

	@Resource
	private IRuleService ruleService;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Resource(name = "cvsMongoTemplate")
	private MongoTemplate cvsMongoTemplate;

	@Resource(name = "userMongoTemplate")
	private MongoTemplate userTemplate;

	public Status<String> action(CVencryptCheck cVencryptCheck, int id) {
		// 更新时间
		String uptime = cVencryptCheck.getCvask().getUptime();
		Date date = QDencrypt.getTime(uptime);
		if (cVencryptCheck.getCheckEnum().equals(CVencryptCheckEnum.FullMatch)
				|| cVencryptCheck.getCheckEnum().equals(CVencryptCheckEnum.MobileMatch)) {
			Set<CVencrypt> m = cVencryptCheck.getMn();
			if (CollectionUtils.isNotEmpty(m)) {
				for (CVencrypt cv : m) {
					if (cv != null) {
						asyncTaskService.updateTimeByCvid(cv.getCvid(), cv.getUid(),
								cVencryptCheck.getCvask().getCoid(), date);
					}
				}
			}

		}
		if (cVencryptCheck.getCvIdCoId() != null && StringUtils.isNoneEmpty(cVencryptCheck.getCvIdCoId().getCvId())) {
			asyncTaskService.updateTimeByCvid(cVencryptCheck.getCvIdCoId().getCvId(),
					cVencryptCheck.getCvIdCoId().getUid(), cVencryptCheck.getCvIdCoId().getCoId(), date);
		}
		return ruleService.run(cVencryptCheck, id);
	}

	public List<CvInfoBo> findAll(CVencryptCheck cVencryptCheck) {
		if (CollectionUtils.isNotEmpty(cVencryptCheck.getMn())) {
			Set<String> cvIds = Sets.newHashSet();
			for (CVencrypt cVencrypt : cVencryptCheck.getMn()) {
				cvIds.add(cVencrypt.getCvid());
			}
			Query query = Query.query(Criteria.where("_id").in(cvIds));
			query.fields().include("uid").include("lRefTime");
			return cvsMongoTemplate.find(query, CvInfoBo.class, "cvInfoBo");
		}
		return null;
	}

	public CvInfoBo findOne(CVencryptCheck cVencryptCheck) {
		List<CvInfoBo> cvInfoBos = findAll(cVencryptCheck);
		CvInfoBo cvInfoBo = null;
		if (cvInfoBos != null) {
			for (CvInfoBo cv : cvInfoBos) {
				if (cvInfoBo == null) {
					cvInfoBo = cv;
				} else {
					if (cv.getLModTime() > cvInfoBo.getLModTime()) {
						cvInfoBo = cv;
					}
				}
			}
		}
		return cvInfoBo;

	}

	/**
	 * @param cvIdCoId
	 * @return
	 */
	public CvInfoBo findOne(String cvid) {
		if (StringUtils.isNotEmpty(cvid)) {
			Query query = Query.query(Criteria.where("_id").is(cvid));
			query.fields().include("uid").include("lRefTime");
			CvInfoBo cvInfoBo = cvsMongoTemplate.findOne(query, CvInfoBo.class, "cvInfoBo");
			if (cvInfoBo != null) {
				return cvInfoBo;
			}
		}
		return null;
	}

	public Map getLogin(String uid) {
		Query query = Query.query(Criteria.where("_id").is(uid));
		query.fields().slice("lLogin", 1);
		return userTemplate.findOne(query, Map.class, "jobseekersBo");
	}

	public Map getReg(String uid) {
		Query query = Query.query(Criteria.where("_id").is(uid));
		query.fields().slice("regTime", 1);
		return userTemplate.findOne(query, Map.class, "jobseekersBo");
	}
}
