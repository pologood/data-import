package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.bj58.chr.auth.AbstractValidate;
import com.bj58.chr.auth.LoginStatus;
import com.bj58.chr.auth.Menu;
import com.bj58.chr.auth.MenuFilter;
import com.bj58.chr.auth.service.IMenuBeanFactoryService;
import com.bj58.chr.common.IPUtil;
import com.bj58.chr.data.controller.SuperController;
import com.bj58.chr.data.model.Manager;
import com.bj58.chr.data.service.IManagerService;

public class LoginValidateService extends AbstractValidate {

	private final static Logger LOG = LoggerFactory.getLogger(LoginValidateService.class);

	@Resource
	private IMenuBeanFactoryService menuBeanFactoryService;

	@Resource
	private IManagerService managerService;

	@Override
	public IMenuBeanFactoryService getMenuBeanFactoryService() {
		return menuBeanFactoryService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

	}

	@Override
	public boolean checkLogin(HttpServletRequest request) {
		String ip = IPUtil.getClientIP(request);
		if (LOG.isDebugEnabled()) {
			LOG.debug(ip);
		}
		if ("220.249.49.20".equals(ip) || "10.0.0.1".equals(ip) || "127.0.0.1".equals(ip) || ip.startsWith("10.152")) {
			Manager manager = new Manager();
			manager.setRoot(true);
			setManager(request, manager);
			return true;
		}
		return false;
	}

	@Override
	public String getLoginURL(HttpServletRequest request) {
		return "/login";
	}

	/**
	 * 检查权限
	 */
	@Override
	public boolean checkAuth(HttpServletRequest request) {
		Manager manager = (Manager) getManager(request);
		if (manager != null) {
			String uri = request.getRequestURI();
			request.setAttribute("menus", manager.isRoot() ? getMenuBeanFactoryService().getAllMenu()
					: new MenuFilter(manager.getRole().getMenusArray()).fitler());
			Menu menu = menuBeanFactoryService.getCurrentMenu(uri);
			request.setAttribute("currentMenu", menu);
			request.setAttribute(SuperController.MANAGER, manager);
			request.setAttribute("systemName", "巧达数据导入管理后台");
			request.setAttribute("suffix", SuperController.URL_SUFFIX);
			return true;
		}
		return false;
	}

	@Override
	public LoginStatus setLogin(HttpServletRequest request, HttpServletResponse response) {
		Object obj = request.getAttribute(SuperController.MANAGER);
		if (obj != null) {
			Manager manager = (Manager) obj;
			request.getSession().setAttribute(this.getManagerKey(), manager);
			return LoginStatus.success(manager.getId());
		}
		return LoginStatus.fail("用户名不存在或者密码错误");
	}

}
