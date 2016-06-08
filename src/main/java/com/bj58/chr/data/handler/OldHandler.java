package com.bj58.chr.data.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.IRuleService;
import com.bj58.chr.data.service.IStatService;
import com.bj58.chr.data.utils.QDStringUtils;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 上午10:50:19
 * @see
 * @since
 */
@Service("oldHandler")
public class OldHandler extends AbstractHandler {

	private final static Logger LOG = LoggerFactory.getLogger(OldHandler.class);

	@Resource
	private IContractService contractService;

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	@Resource
	private IRuleService ruleService;

	@Resource(name = "cvsMongoTemplate")
	private MongoTemplate cvsMongoTemplate;

	@Resource(name = "userMongoTemplate")
	private MongoTemplate userTemplate;

	@Value("${old.full.match}")
	private int oldFullMatch;

	@Value("${old.mobile.match}")
	private int oldMobileMatch;

	@Value("${old.email.match}")
	private int oldEmailMatch;

	@Value("${old.not.match}")
	private int oldNotMatch;

	@Resource
	private IStatService statService;

	@Override
	public Status<String> doFullMatch(CVencryptCheck cVencryptCheck) {
		super.action(cVencryptCheck, oldFullMatch);
		statService.askOldFull();
		return check(cVencryptCheck);
	}

	@Override
	public Status<String> doMobileMatch(CVencryptCheck cVencryptCheck) {// 部分匹配
		super.action(cVencryptCheck, oldEmailMatch);
		statService.askOldM();
		return check(cVencryptCheck);
	}

	@Override
	public Status<String> doEmailMatch(CVencryptCheck cVencryptCheck) {// 部分匹配
		action(cVencryptCheck, oldEmailMatch);
		statService.askOldNo();
		return check(cVencryptCheck);
	}

	@Override
	public Status<String> doNotMatch(CVencryptCheck cVencryptCheck) {
		action(cVencryptCheck, oldNotMatch);
		statService.askOldNo();
		return check(cVencryptCheck);
	}

	private final static long D30 = 60 * 60 * 24 * 30;

	public Status<String> check(CVencryptCheck cVencryptCheck) {
		Status<String> result = Status.newStatus(300, "不需要这份简历");
		if (LOG.isDebugEnabled()) {
			LOG.debug(cVencryptCheck.toString());
		}
		CvInfoBo cvInfoBo = null;
		long time = QDStringUtils.getCvTime(System.currentTimeMillis());
		// if (CollectionUtils.isNotEmpty(cVencryptCheck.getMn())) {
		// cvInfoBo = findOne(cVencryptCheck);
		// }
		if (cVencryptCheck.getCvIdCoId() != null) {
			LOG.info("get cvInfoBo by" + cVencryptCheck.getCvIdCoId().getCvId());
			cvInfoBo = findOne(cVencryptCheck.getCvIdCoId().getCvId());
		}
		LOG.info("get cvInfoBo" + cvInfoBo);
		if (cvInfoBo != null) {
			LOG.info(cvInfoBo.getUid() + " cv update time:" + time + "-" + cvInfoBo.getLRefTime());
			// if (time - cvInfoBo.getLRefTime() > D7) {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(cvInfoBo.getUid()));
			query.fields().slice("lLogin", 1);
			@SuppressWarnings("rawtypes")
			Map params = userTemplate.findOne(query, Map.class, "jobseekersBo");
			Object o = params.get("lLogin");
			if (o != null) {
				long c = NumberUtils.toLong(String.valueOf(o));
				LOG.info(cvInfoBo.getUid() + " login time :" + time + "-" + c);
				if (time - c > D30) {// 30天没登录
					LOG.info("old out 30");
					result = Status.newStatus(202, "请求更新简历");
				}
			}
			// }
		}

		statService.askOldReject();
		return result;
	}

	@Override
	public Status<String> action(CVencryptCheck cVencryptCheck, int id) {
		// String coid = cVencryptCheck.getCvask().getCoid();
		// if (getContract(cVencryptCheck.getCvask().getCoid())) {
		// asyncTaskService.updateContract(coid);
		// }
		return super.action(cVencryptCheck, id);
	}

	private boolean getContract(String coid) {
		CVContract contract = contractService.findByCoId(coid);
		if (contract == null || contract.isFresh()) {// 代表没要过联系方式
			return false;
		}
		return contract.getStatus() == 1;
	}

}
