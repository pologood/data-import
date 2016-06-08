package com.bj58.chr.data.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.Validate;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.data.model.Manager;
import com.bj58.chr.data.service.IManagerService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月9日 下午1:20:55
 * @see
 * @since
 */
@Controller
@RequestMapping(value = "/")
public class PageController extends SuperController {

	@Resource
	private Validate validate;

	@Resource
	private IManagerService managerService;

	@RequestMapping(value = "/")
	public ModelAndView index(HttpServletRequest request) {
		return forward("index");
	}

	@RequestMapping(value = "")
	public ModelAndView index2(HttpServletRequest request) {
		return forward("index");
	}

	@RequestMapping("/login")
	public ModelAndView login(@ModelAttribute Manager manager, HttpServletResponse response,
			HttpServletRequest request) {
		if (WebUtils.isPOST(request)) {
			Manager dbManager = managerService.login(manager);
			if (dbManager != null && dbManager.getPrimaryKey() > 0) {
				request.setAttribute(SuperController.MANAGER, dbManager);
				validate.login(request, response);
				return redirect("/");
			} else {
				return forward("login").addObject("error", "用户名或者密码错误");
			}
		}
		return forward("login");
	}

	@RequestMapping("/logout")
	public ModelAndView login(HttpServletResponse response, HttpServletRequest request) {
		validate.logout(response);
		request.getSession().invalidate();
		return redirect("/login");
	}

}
