package com.bj58.chr.data.toolbox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bj58.chr.api.scf.entity.company.JobMappingBo;
import com.bj58.chr.api.scf.enums.DawnEnum;
import com.bj58.chr.common.CodecUtils;
import com.bj58.chr.common.HttpUtils;
import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.model.wb.CompanyReturnVo;
import com.bj58.chr.data.model.wb.JobReturnVo;
import com.bj58.chr.data.model.wb.RegisterReturnVo;
import com.bj58.chr.data.model.wb.TokenEntity;
import com.bj58.chr.data.model.wb.User58;
import com.bj58.chr.data.model.wb.WorkAddressReturnVo;
import com.bj58.chr.data.service.ICompanyService;
import com.bj58.chr.data.service.IJobBoService;
import com.bj58.chr.data.utils.NameValuePairUtils;
import com.bj58.spat.core.jsr166.ThreadLocalRandom;

@Service
public class Open58Tool {
	private static final Logger logger = LoggerFactory.getLogger(Open58Tool.class);

	private static final String CLIENT_ID = "31909803034113";
	private static final String CLIENT_SECRET = "0j2JpbISPA8xtrDBbrp3";
	private static final String SEED_TOKEN = "5242c9778a1632aa6dc64af7ae4f6227_v1";
	private static final String SEED_UID = "32063573944070";

	// 批量注册
	private static final String REGISTTER_URL = "http://openapi.58.com/oauth2/register";
	// 授权回调
	private static final String REDIRECT_URL = "http://116.213.93.34/oauth";
	private static final String WORKADDRESS_URL = "http://zp.58.com/chinahr/workAddress";

	// private static final String REDIRECT_URL =
	// "http://api.chinahr.com/oauth";
	// 获取授权
	private static final String TOKEN_URL = "http://openapi.58.com/oauth2/access_token";
	// 刷新企业
	private static final String UPDATEENTERPRISE_URL = "http://openapi.58.com/oauth2/gateway/enterprise/updateenterprise";
	// 普通职位刷新
	private static final String COMMONREFRESH_URL = "http://openapi.58.com/oauth2/gateway/info/commonrefresh";
	// 添加企业
	private static final String ADDENTERPRISE_URL = "http://openapi.58.com/oauth2/gateway/enterprise/addenterprise";
	// 发布帖子url
	private static final String ADDJOB_URL = "http://openapi.58.com/oauth2/gateway/postservice/send";
	// 修改帖子url
	private static final String UPDATEJOB_URL = "http://openapi.58.com/oauth2/gateway/postservice/update";
	// 删除帖子url
	private static final String DELETEJOB_URL = "http://openapi.58.com/oauth2/gateway/delservice/delete";

	@Resource
	private IJobBoService jobBoService;

	@Resource
	private ICompanyService companyService;

	public class OPER_TYPE {
		public static final short UNACTIVE = 0;
		public static final short NORMAL = 1;
		public static final short DELETE = 2;
	}

	public TokenEntity getTokenEntity(String code) {
		NameValuePairUtils nvpu = secret();
		nvpu.add("code", code).add("grant_type", "authorization_code").add("grant_type", "authorization_code")
				.add("redirect_uri", REDIRECT_URL);
		String result = HttpUtils.doPost(TOKEN_URL, nvpu.get());
		return JSONUtils.readValue(result, TokenEntity.class);
	}

	public User58 registerUser(String uid, String username) {
		if (username.getBytes().length > 40) {
			logger.info("用户注册失败,uid:" + uid);
			return null;
		}
		NameValuePairUtils nvpu = secret();
		nvpu.add("uniID", uid).add("username", username).add("redirect_uri", REDIRECT_URL).add("58user_id", SEED_UID)
				.add("access_token", SEED_TOKEN);
		String result = retry(REGISTTER_URL, nvpu);
		RegisterReturnVo returnVo = JSONUtils.readValue(result, RegisterReturnVo.class);
		if (returnVo != null && !StringUtils.isBlank(returnVo.getGateway_success())
				&& returnVo.getGateway_success().equals("1")) {
			User58 user = JSONUtils.readValue(result, User58.class);
			user.setUsername(username);
			logger.info("58api registerUser,uid:" + uid + result);
			return user;
		} else {
			String unameExp = username + ThreadLocalRandom.current().nextInt(0, 9);
			return registerUser(uid, unameExp);
		}
	}

	public boolean sycnCompany(String comId) {
		logger.info("start sycnCompany comId:" + comId);
		Map<String, String> params = companyService.getCompanyParam(comId);
		if (params == null) {
			logger.info("公司信息不满足同步要求！" + comId);
			return false;
		}
		NameValuePairUtils nvpu = secret();
		String result = retry(ADDENTERPRISE_URL, nvpu);
		CompanyReturnVo returnVo = JSONUtils.readValue(result, CompanyReturnVo.class);
		if (returnVo != null && !StringUtils.isBlank(returnVo.getGateway_success())
				&& returnVo.getGateway_success().equals("1") && !returnVo.getApimsg().contains("-1")) {
			companyService.setCompanySyncFlag(comId);
			logger.info("58api sycnCompany success,comId:" + comId + returnVo.getApimsg() + result);
			return true;
		} else if (returnVo != null && returnVo.getApimsg().contains("已经存在企业")) {
			companyService.setCompanySyncFlag(comId);
			logger.info("58api sycnCompany success,comId:" + comId + returnVo.getApimsg() + result);
			return true;
		} else {
			if (returnVo != null) {
				logger.warn("58api sycnCompany fail,comId:" + comId + returnVo.getApimsg() + result);
			} else {
				logger.warn("58api sycnCompany fail,comId:" + comId + " result:" + result);
				logger.warn("58api sycnCompany fail,comId:" + comId + " params:" + JSONUtils.writeValue(params));
			}
			return false;
		}
	}

	public void updateCompany(String comId) {
		NameValuePairUtils nvpu = secret();
		String result = retry(UPDATEENTERPRISE_URL, nvpu);
		CompanyReturnVo returnVo = JSONUtils.readValue(result, CompanyReturnVo.class);
		if (returnVo != null && !StringUtils.isBlank(returnVo.getGateway_success())
				&& returnVo.getGateway_success().equals("1")) {
			logger.info("58api updateCompany success comid:" + comId + returnVo.getApimsg() + result);
		} else {
			logger.info("58api updateCompany fail comid:" + comId + returnVo.getApimsg() + result);
		}
	}

	public void refreshJob(String infoId, String userId58, String userToken) {
		NameValuePairUtils nvpu = secret();
		nvpu.add("infoid", infoId).add("58user_id", userId58).add("access_token", userToken);
		String result = retry(COMMONREFRESH_URL, nvpu);
		CompanyReturnVo returnVo = JSONUtils.readValue(result, CompanyReturnVo.class);
		if (returnVo != null && !StringUtils.isBlank(returnVo.getGateway_success())
				&& returnVo.getGateway_success().equals("1")) {
			logger.info("58api refreshJob success,jobId:" + infoId + returnVo.getApimsg() + result);
		} else {
			logger.info("58api refreshJob fail,jobId:" + infoId + result);
		}
	}

	public void deleteJob(String infoId, String userId58, String userToken) {
		NameValuePairUtils nvpu = secret();
		nvpu.add("infoid", infoId).add("58user_id", userId58).add("access_token", userToken);
		String result = HttpUtils.doPost(DELETEJOB_URL, nvpu.get());
		CompanyReturnVo returnVo = JSONUtils.readValue(result, CompanyReturnVo.class);
		if (returnVo != null && !StringUtils.isBlank(returnVo.getGateway_success())
				&& returnVo.getGateway_success().equals("1")) {
			logger.info("58api deleteJob success,jobId:" + infoId + returnVo.getApimsg() + result);
		} else {
			logger.info("58api deleteJob fail,jobId:" + infoId + returnVo.getApimsg() + result);
		}
	}

	public boolean sycnJobs(String jobId, String comId, String userId58, String userToken) {
		Map<String, Map<String, String>> map = jobBoService.findJobParams(jobId);
		if (map == null) {
			logger.info("58api sycnJobs fail jobid:" + jobId);
			return false;
		}
		boolean flag = false;
		for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
			NameValuePairUtils nvpu = secret();
			nvpu.add(entry.getValue());
			nvpu.add("58user_id", userId58).add("access_token", userToken);
			String result = retry(ADDJOB_URL, nvpu);
			JobReturnVo returnVo = JSONUtils.readValue(result, JobReturnVo.class);
			if (null != returnVo && !StringUtils.isBlank(returnVo.getInfoid())) {
				JobMappingBo bo = new JobMappingBo();
				bo.setCompanyId(comId);
				bo.setType(DawnEnum.JOBMAPING_TYPE.JOB58.getValue());
				bo.setInId(jobId);
				bo.setOutId(returnVo.getInfoid());
				bo.setState(DawnEnum.JOBMAPING_STATE.NORMAL.getValue());
				String cityAndCate = entry.getKey();
				String[] cityAndCates = cityAndCate.split(",");
				bo.setCateId(Integer.valueOf(cityAndCates[1]));
				bo.setCityId(Integer.valueOf(cityAndCates[0]));
				jobBoService.insertJobMapping(bo);
				logger.info("58api sycnJobs success jobid:" + jobId + result);
				flag = true;
			} else {
				logger.info("58api sycnJobs fail jobid:" + jobId + result);
			}
		}
		return flag;
	}

	/**
	 * <p>
	 * Description: 修改职位信息
	 * </p>
	 * 
	 * @param jobId
	 *            职位编号
	 * @param comId
	 *            公司编号
	 * @throws @author
	 *             lifen
	 * @date 2015年6月24日 下午1:58:27
	 */
	public void updateJobs(String jobId, String comId, String userId58, String userToken) {
		logger.info(String.format("58api updateJobs fail jobId:%,comId:%,userId58:%,userToken:%.", jobId, comId,
				userId58, userToken));
		Map<String, Map<String, String>> map = jobBoService.findJobParams(jobId);
		for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
			NameValuePairUtils nvpu = secret();
			nvpu.add(entry.getValue()).add("58user_id", userId58).add("access_token", userToken);
			String result = retry(UPDATEJOB_URL, nvpu);
			JobReturnVo returnVo = JSONUtils.readValue(result, JobReturnVo.class);
			if (null != returnVo && !StringUtils.isBlank(returnVo.getGateway_success())
					&& "1".equals(returnVo.getGateway_success())) {
				logger.info("58api updateJobs success jobid:" + jobId + result);
			} else {
				logger.warn("58api updateJobs fail jobid:" + jobId + result);
				logger.warn("58api updateJobs fail params:" + JSONUtils.writeValue(returnVo));
			}
		}

	}

	public String addWorkAddress(String userid, String address, String localid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(new Date());
		String validateKey = CodecUtils.md5(currentDate + userid);
		NameValuePairUtils nvpu = NameValuePairUtils.create();
		nvpu.add("key", validateKey).add("userid", userid).add("address", address).add("localid", localid);
		String result = retry(WORKADDRESS_URL, nvpu);

		WorkAddressReturnVo returnVo = JSONUtils.readValue(result, WorkAddressReturnVo.class);
		if (returnVo != null && !StringUtils.isBlank(returnVo.getResultCode())
				&& !returnVo.getResultCode().equals("-1")) {
			logger.info("58api addWorkAddress success ,uid:" + userid + result);
			return returnVo.getWorkaddressid();
		} else {
			logger.info("58api addWorkAddress fail ,uid:" + userid + result);
		}
		return "";
	}

	// private <T> Boolean getValue(String result, Class<T> cls, Handler<T>
	// handler) {
	// T t = JSONUtils.readValue(result, cls);
	// return handler.execute(t);
	// }

	private NameValuePairUtils secret() {
		NameValuePairUtils nvpu = NameValuePairUtils.create();
		long currentTime = System.currentTimeMillis();
		String secret = CLIENT_SECRET + "openapi.58.com" + currentTime;
		String md5Str = CodecUtils.md5(secret);
		nvpu.add("client_secret", md5Str).add("time_sign", Long.toString(currentTime)).add("client_id", CLIENT_ID);
		return nvpu;
	}

	private String retry(String web, NameValuePairUtils nvpu) {
		int errorCount = 0;
		String result = null;
		do {
			result = HttpUtils.doPost(REGISTTER_URL, nvpu.get());
			if (StringUtils.isBlank(result)) {
				errorCount++;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			} else {
				break;
			}
		} while (errorCount <= 3);
		return result;
	}

}
