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
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.web.page.PageRequest;
import com.bj58.chr.data.model.Manager;
import com.bj58.chr.data.model.Role;
import com.bj58.chr.data.service.IManagerService;
import com.bj58.chr.data.service.IRoleService;

@Controller
@MenuType(name = "权限管理", index = 100)
@RequestMapping("/manager")
public class ManagerController extends SuperController {

	@Resource
	private IManagerService managerService;
	@Resource
	private IRoleService roleService;

	@RequestMapping(value = "/add")
	@MenuType(name = "添加用户", catalog = true)
	public ModelAndView add(@ModelAttribute Manager manager, BindingResult result, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		if (WebUtils.isGET(request)) {
			List<Role> roles = roleService.list();
			request.setAttribute("roles", roles);
			modelAndView = forward("/manager/add");
		} else {
			if (!manager.isRoot()) {
				Role role = manager.getRole();
				if (role == null || role.getId() == null || role.getId() == 0) {
					result.addError(new FieldError("manager", "role", "*请选择一个角色"));
				}
			}
			if (result.hasErrors()) {
				List<Role> roles = roleService.list();
				request.setAttribute("roles", roles);
				modelAndView = forward("/manager/add");
			} else {
				if (checkbox(request, "root")) {
					manager.setRoot(true);
					Role role = new Role();
					role.setId(0);
					manager.setRole(role);
				}
				managerService.saveOrUpdate(manager);
				modelAndView = redirect("/chr-page-manager/manager/list");
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/edit")
	@MenuType(name = "修改用户")
	public ModelAndView edit(HttpServletRequest request) {
		Manager manager = managerService.findById(WebUtils.findInt(request, "id"));
		List<Role> roles = roleService.list();
		request.setAttribute("roles", roles);
		return forward("/manager/add").addObject("manager", manager);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MenuType(name = "删除用户")
	public ModelAndView delete(HttpServletRequest request) {
		managerService.remove(WebUtils.findInt(request, "id"));
		return redirect("/chr-page-manager/manager/list", "page=" + WebUtils.findString(request, "page"));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@MenuType(name = "用户列表", catalog = true)
	public ModelAndView list(HttpServletRequest request) {
		PageRequest pageRequest = builderPage(request);
		return forward("/manager/list", request).addObject("data", managerService.listSlice(pageRequest))
				.addObject("uri", "/manager/list");
	}

}
