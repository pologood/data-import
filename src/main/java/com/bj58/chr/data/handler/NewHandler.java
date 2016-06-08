package com.bj58.chr.data.handler;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.Need;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.INeedService;
import com.bj58.chr.data.service.IRuleService;
import com.bj58.chr.data.service.IStatService;
import com.bj58.chr.data.utils.QDStringUtils;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 上午10:50:31
 * @see
 * @since
 */
@Service("newHandler")
public class NewHandler extends AbstractHandler {

	private final static Logger LOG = LoggerFactory.getLogger(NewHandler.class);

	@Resource
	private INeedService needService;

	@Resource
	private ICvInfoBoService cvInfoBoService;

	@Resource
	private IRuleService ruleService;

	@Value("${new.full.match}")
	private int newFullMatch;

	@Value("${new.mobile.match}")
	private int newMobileMatch;

	@Value("${new.email.match}")
	private int newEmailMatch;

	@Value("${new.not.match}")
	private int newNotMatch;

	@Resource(name = "cvsMongoTemplate")
	private MongoTemplate cvsMongoTemplate;

	@Resource(name = "userMongoTemplate")
	private MongoTemplate userTemplate;

	@Resource
	private IStatService statService;

	@Override
	public Status<String> doFullMatch(CVencryptCheck cVencryptCheck) {
		// 判断简历是否更新，通过规则
		action(cVencryptCheck, newFullMatch);// 规则可能不一致，但是后续逻辑一直
		statService.askNewFull();
		return check(cVencryptCheck);
	}

	@Override
	public Status<String> doMobileMatch(CVencryptCheck cVencryptCheck) {
		// 判断简历是否更新，通过规则
		action(cVencryptCheck, newMobileMatch);
		statService.askNewM();
		return check(cVencryptCheck);
	}

	private final static long D30 = 60 * 60 * 24 * 30;

	public Status<String> check(CVencryptCheck cVencryptCheck) {
		Status<String> result = Status.newStatus(300, "不需要这份简历");
		if (CollectionUtils.isNotEmpty(cVencryptCheck.getMn())) {
			// 多个简历，判断那个最新的
			long time = QDStringUtils.getCvTime(System.currentTimeMillis());
			Iterator<CVencrypt> cvencrypt = cVencryptCheck.getMn().iterator();
			String uid = null;
			while (cvencrypt.hasNext()) {
				uid = cvencrypt.next().getUid();
				if (StringUtils.isNotEmpty(uid)) {
					break;
				}
			}
			if (uid != null) {
				Query query = new Query();
				query.addCriteria(Criteria.where("_id").is(uid));
				query.fields().slice("lLogin", 1);
				@SuppressWarnings("rawtypes")
				Map params = userTemplate.findOne(query, Map.class, "jobseekersBo");
				Object o = params.get("lLogin");
				if (o != null) {
					long c = NumberUtils.toLong(String.valueOf(o));
					if (time - c > D30) {// 30天没登录
						LOG.info("new out 30");
						result = Status.newStatus(201, "英才有这个简历，请求内容");
					}
				}
			}
		}
		statService.askNewReject();
		return result;
	}

	@Override
	public Status<String> doEmailMatch(CVencryptCheck cVencryptCheck) {
		statService.askNewNo();
		return action(cVencryptCheck, newEmailMatch);
	}

	@Override
	public Status<String> doNotMatch(CVencryptCheck cVencryptCheck) {
		Need need = new Need(cVencryptCheck.getCvask().getCoid());// 需要的话，记录需求
		needService.save(need);
		statService.askNewNo();
		return action(cVencryptCheck, newNotMatch);
	}

}
