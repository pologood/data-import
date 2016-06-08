package com.bj58.chr.data.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.web.page.PageRequest;
import com.bj58.chr.data.model.Config;
import com.bj58.chr.data.service.IConfigService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午3:11:39
 * @see
 * @since
 */

@Controller
@MenuType(name = "配置中心", index = 100)
@RequestMapping("/config")
public class ConfigController extends SuperController {

	@Resource
	private IConfigService configService;

	@RequestMapping(value = "/add")
	@MenuType(name = "添加配置", catalog = true)
	public ModelAndView add(@ModelAttribute Config config, BindingResult result, HttpServletRequest request) {
		if (WebUtils.isPOST(request)) {
			validate(config, result);
			if (!result.hasErrors()) {
				configService.saveOrUpdate(config);
				return redirect("/config/list");
			}
		}
		return forward("/config/add");
	}

	@RequestMapping(value = "/edit")
	@MenuType(name = "修改配置")
	public ModelAndView edit(HttpServletRequest request) {
		Config config = configService.findById(WebUtils.findInt(request, "id"));
		return forward("/config/add").addObject("config", config);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MenuType(name = "删除配置")
	public ModelAndView delete(HttpServletRequest request) {
		configService.remove(WebUtils.findInt(request, "id"));
		return redirect("/config/list", "page=" + WebUtils.findString(request, "page"));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@MenuType(name = "配置列表", catalog = true)
	public ModelAndView list(HttpServletRequest request) {
		PageRequest pageRequest = builderPage(request);
		return forward("/config/list", request).addObject("data", configService.listSlice(pageRequest)).addObject("uri",
				"/config/list");
	}

}
