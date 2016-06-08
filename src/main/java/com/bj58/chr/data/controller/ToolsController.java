package com.bj58.chr.data.controller;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.ICVencryptService;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.service.ICvIdCoIdService2;
import com.google.common.collect.Lists;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月9日 下午10:28:36
 * @see
 * @since
 */
@Controller
@MenuType(name = "在线工具", index = 100)
@RequestMapping("/tools")
public class ToolsController extends SuperController {

	private final static Logger LOG = LoggerFactory.getLogger(ToolsController.class);

	@Resource
	private ICvIdCoIdService2 cvIdCoIdService2;

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Resource
	private ICVencryptService cVencryptService;

	@RequestMapping(value = "/coid")
	@MenuType(name = "通过简历ID获得合作ID", catalog = true)
	public ModelAndView coid(HttpServletRequest request) {
		final String cvId = WebUtils.findString(request, "cvId");
		ModelAndView modelAndView = forward("/tools/coid");
		if (StringUtils.isNotEmpty(cvId)) {
			final CountDownLatch countDownLatch = new CountDownLatch(30);
			final List<String> cvids = Lists.newArrayList();
			for (int i = 0; i < 30; i++) {
				final String table = "cv_idcoid_" + i;
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							List<CvIdCoId> list = cvIdCoIdService2
									.getByCvId(ParamUtils.createParam().add("table", table).add("cv_id", cvId).get());
							if (list != null && list.size() > 0) {
								for (CvIdCoId cvIdCoId : list) {
									cvids.add(cvIdCoId.getCoId());
									LOG.info(cvIdCoId.toString());
								}
							}
						} finally {
							countDownLatch.countDown();
						}
					}
				}).start();
			}
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			modelAndView.addObject("data", cvids);
		}
		return modelAndView.addObject("cvId", cvId);
	}

	@RequestMapping(value = "/cid")
	@MenuType(name = "通过合作ID获得简历ID", catalog = true)
	public ModelAndView cvid(HttpServletRequest request) {
		final String coId = WebUtils.findString(request, "coId");
		ModelAndView modelAndView = forward("/tools/cvid");
		if (StringUtils.isNotEmpty(coId)) {
			CvIdCoId cvIdCoId = cvIdCoIdService.findById(new CvIdCoId(coId));
			LOG.info(cvIdCoId.toString());
			modelAndView.addObject("cvIdCoId", cvIdCoId);
		}
		return modelAndView.addObject("coId", coId);
	}

	@RequestMapping(value = "/digest")
	@MenuType(name = "加密串获得内容", catalog = true)
	public ModelAndView digest(HttpServletRequest request) {
		String digest = WebUtils.findString(request, "digest");
		ModelAndView modelAndView = forward("/tools/digest");
		if (StringUtils.isNotEmpty(digest)) {
			List<CVencrypt> cVencrypt = cVencryptService.findByEncrypt(digest);
			modelAndView.addObject("cVencrypt", cVencrypt);
		}
		return modelAndView.addObject("digest", digest);
	}

}
