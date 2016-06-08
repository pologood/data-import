package com.bj58.chr.data.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bj58.chr.api.scf.entity.company.CompanyInfoBo;
import com.bj58.chr.api.scf.entity.company.JobsBo;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.data.model.wb.User58;
import com.bj58.chr.data.service.ICompanyService;
import com.bj58.chr.data.service.IJobBoService;
import com.bj58.chr.data.toolbox.Open58Tool;
import com.bj58.chr.data.utils.RedisHeper;
import com.google.common.collect.Maps;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月19日 下午9:26:57
 * @see
 * @since
 */
@Controller
@RequestMapping(value = "/sycnjob")
public class WBController extends SuperController {

	private final static Logger LOG = LoggerFactory.getLogger(WBController.class);

	private final static RedisHeper redisHeper = RedisHeper.getRedisHeper();

	@Resource
	private IJobBoService jobBoService;

	@Resource
	private ICompanyService companyService;

	@Resource
	private Open58Tool open58Tool;

	@RequestMapping(value = "/{jobId}", method = RequestMethod.POST)
	public Map<Boolean, Object> job(@PathVariable("jobId") String jobId, HttpServletRequest request) {
		Map<Boolean, Object> result = Maps.newHashMap();
		if (redisHeper.setex(jobId, 3600, StringUtils.EMPTY) != null) {
			result.put(false, "同步职位失败，重复的同步职位操作。");
		}
		JobsBo job = jobBoService.findById(jobId);
		if (job == null) {
			LOG.info("同步职位失败，未找到对应职位，jobId：" + jobId);
			result.put(false, "同步职位失败，未找到对应职位!!!");
		}
		String comId = job.getComId();
		CompanyInfoBo comBo = companyService.findById(comId);
		if (null == comBo) {
			result.put(false, "公司信息为空！");
		} else {
			if (!companyService.isEvenSycnJob(comBo)) {
				String buid = companyService.getBuild(comBo);
				User58 user58 = open58Tool.registerUser(buid, comBo.getuName());
				if (user58 == null) {
					return null;
				}
				companyService.updAccessToken(comId, user58);
			}
		}
		return result;
	}

	@RequestMapping(value = "/sycnjob", method = RequestMethod.POST)
	public void sycnjob(HttpServletRequest request) {
		String jobId = WebUtils.findString(request, "jobId");
		Map<Boolean, Object> result = Maps.newHashMap();
		if (redisHeper.setex(jobId, 3600, StringUtils.EMPTY) != null) {
			result.put(false, "同步职位失败，重复的同步职位操作。");
		}
	}

}
