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
import com.bj58.chr.data.model.Rule;
import com.bj58.chr.data.service.IRuleService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午5:15:17
 * @see
 * @since
 */

@Controller
@MenuType(name = "规则配置", index = 100)
@RequestMapping("/rule")
public class RuleController extends SuperController {

	@Resource
	private IRuleService ruleService;

	@RequestMapping(value = "/add")
	@MenuType(name = "添加规则", catalog = true)
	public ModelAndView add(@ModelAttribute Rule rule, BindingResult result, HttpServletRequest request) {
		if (WebUtils.isPOST(request)) {
			validate(rule, result);
			if (!result.hasErrors()) {
				ruleService.saveOrUpdate(rule);
				return redirect("/rule/list");
			}
		}
		return forward("/rule/add");
	}

	@RequestMapping(value = "/edit")
	@MenuType(name = "修改规则")
	public ModelAndView edit(HttpServletRequest request) {
		Rule rule = ruleService.findById(WebUtils.findInt(request, "id"));
		return forward("/rule/add").addObject("rule", rule);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MenuType(name = "删除规则")
	public ModelAndView delete(HttpServletRequest request) {
		ruleService.remove(WebUtils.findInt(request, "id"));
		return redirect("/rule/list", "page=" + WebUtils.findString(request, "page"));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@MenuType(name = "规则列表", catalog = true)
	public ModelAndView list(HttpServletRequest request) {
		PageRequest pageRequest = builderPage(request);
		return forward("/rule/list", request).addObject("data", ruleService.listSlice(pageRequest));
	}

}
