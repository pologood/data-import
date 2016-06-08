package com.bj58.chr.data.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.auth.service.IMenuFactoryService;
import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.web.page.PageRequest;
import com.bj58.chr.data.model.Role;
import com.bj58.chr.data.service.IRoleService;

@Controller
@MenuType(name = "权限管理", index = 100)
@RequestMapping("/role")
public class RoleController extends SuperController {
	@Resource
	private IRoleService roleService;

	@Resource
	private IMenuFactoryService menuFactoryService;

	@RequestMapping(value = "/add")
	@MenuType(name = "添加角色", catalog = true)
	public ModelAndView add(@ModelAttribute Role role, BindingResult result, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		List<String> menus = role.getMenusArray();
		if (WebUtils.isGET(request)) {
			modelAndView = forward("/role/add");
			modelAndView.addObject("jsonMenu", JSONUtils.writeValue(menuFactoryService.getAllMenu()));
			modelAndView.addObject("selector", JSONUtils.writeValue(menus));
		} else {
			if (menus == null || menus.size() == 0) {
				modelAndView = forward("/role/add");
				result.addError(new FieldError("menus", "menus", "你没有选择任何权限"));
				modelAndView.addObject("jsonMenu", JSONUtils.writeValue(menuFactoryService.getAllMenu()));
				modelAndView.addObject("selector", JSONUtils.writeValue(menus));
			} else {
				roleService.saveOrUpdate(role);
				modelAndView = redirect("/role/list");
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/edit")
	@MenuType(name = "角色修改")
	public ModelAndView edit(HttpServletRequest request) {
		int id = WebUtils.findInt(request, "id");
		Role role = roleService.findById(id);
		ModelAndView modelAndView = forward("role/add");
		modelAndView.addObject("jsonMenu", JSONUtils.writeValue(menuFactoryService.getAllMenu()));
		modelAndView.addObject("selector", JSONUtils.writeValue(role.getMenusArray()));
		modelAndView.addObject("role", role);
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MenuType(name = "删除角色")
	public ModelAndView delete(HttpServletRequest request) {
		int id = WebUtils.findInt(request, "id");
		roleService.remove(id);
		return redirect("/role/list?page=" + WebUtils.findString(request, "page"));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@MenuType(name = "角色列表", catalog = true)
	public ModelAndView list(HttpServletRequest request) {
		PageRequest pageRequest = builderPage(request);
		return forward("/role/list", request).addObject("data", roleService.listSlice(pageRequest));
	}

}
