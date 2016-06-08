package com.bj58.chr.data.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.common.web.Status;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.service.ICheckSerivce;
import com.bj58.chr.data.service.impl.CVencryptService;
import com.google.common.collect.Maps;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月12日 下午6:03:58
 * @see
 * @since
 */
@Controller
@MenuType(name = "早期数据核查", index = 100)
@RequestMapping("/db")
public class DataRunController extends SuperController {

	@Resource
	private ICheckSerivce checkService;

	@Resource
	private CVencryptService cVencryptService;

	@RequestMapping(value = "/check")
	@MenuType(name = "运行核查", catalog = true)
	public ModelAndView add(HttpServletRequest request) {
		String file = WebUtils.findString(request, "file");
		if (!StringUtils.isEmpty(file)) {
			checkService.checkNew(file);
		}
		return forward("/db/check");
	}

	@RequestMapping(value = "/coid")
	@MenuType(name = "合作ID检查")
	@ResponseBody
	public Status<HashMap<Object, Object>> coid(HttpServletRequest request) {
		String coId = WebUtils.findString(request, "coId");
		if (!StringUtils.isEmpty(coId)) {
			return Status.success(checkService.checkCoId(coId));
		}
		return Status.fail(Maps.newHashMap());
	}

	@RequestMapping(value = "/encrty")
	@MenuType(name = "合作ID检查")
	@ResponseBody
	public Status<List<CVencrypt>> encrty(HttpServletRequest request) {
		String encrty = WebUtils.findString(request, "encrty");
		if (!StringUtils.isEmpty(encrty)) {
			return Status.success(cVencryptService.findByEncrypt(encrty));
		}
		return Status.fail((List<CVencrypt>) null);
	}

}
