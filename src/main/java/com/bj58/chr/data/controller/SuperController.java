package com.bj58.chr.data.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import com.bj58.chr.auth.model.Manager;
import com.bj58.chr.common.web.BaseController;

public class SuperController extends BaseController {

	public final static String SLANT = "/";
	public final static String DOT = ".";
	public final static String URL_SUFFIX = "";
	public final static String MANAGER = "login_manager";

	@Resource
	private SpringValidatorAdapter springValidatorAdapter;

	public void validate(Object entity, BindingResult result) {
		springValidatorAdapter.validate(entity, result);
	}

	public String builderResult(List<FieldError> fieldErrors) {
		StringBuilder buffer = new StringBuilder();
		for (FieldError fieldError : fieldErrors) {
			if (buffer.length() > 0) {
				buffer.append(",");
			}
			buffer.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage());
		}
		return buffer.toString();
	}

	public int getUserId(HttpServletRequest request) {
		Manager<Integer> manager = getUser(request);
		return manager == null ? 0 : manager.getId();
	}

	@SuppressWarnings("unchecked")
	public Manager<Integer> getUser(HttpServletRequest request) {
		Object obj = request.getAttribute(MANAGER);
		if (obj != null) {
			return (Manager<Integer>) obj;
		}
		return null;
	}

	@Override
	public String getUrlSuffix() {
		return URL_SUFFIX;
	}

	@Override
	public int getPageSize() {
		return 12;
	}

	@Override
	public String getPageKey() {
		return "page";
	}

	@Override
	public String getPageSizeKey() {
		return "pagesize";
	}

}
