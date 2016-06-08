package com.bj58.chr.data.controller.kv;

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
import com.bj58.chr.data.controller.SuperController;
import com.bj58.chr.data.model.kv.KVGroup;
import com.bj58.chr.data.service.IKVGroupService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:52:34
 * @see
 * @since
 */
@Controller
@MenuType(name = "字典表管理", index = 2)
@RequestMapping("/kv/group")
public class KVGroupController extends SuperController {

	@Resource
	private IKVGroupService kVGroupService;

	@RequestMapping(value = "/add")
	@MenuType(name = "添加字典分类", catalog = true)
	public ModelAndView add(@ModelAttribute("kVGroup") KVGroup kVGroup, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = null;
		if (WebUtils.isGET(request)) {
			modelAndView = forward("/kv/group/add");
		} else {
			kVGroupService.saveOrUpdate(kVGroup);
			modelAndView = redirect("/kv/group/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/edit")
	@MenuType(name = "字典分类修改")
	public ModelAndView edit(HttpServletRequest request) {
		int id = WebUtils.findInt(request, "id");
		KVGroup kVGroup = kVGroupService.findById(id);
		ModelAndView modelAndView = forward("kv/group/add");
		modelAndView.addObject("kVGroup", kVGroup);
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MenuType(name = "删除字典分类")
	public ModelAndView delete(HttpServletRequest request) {
		int id = WebUtils.findInt(request, "id");
		kVGroupService.remove(id);
		return redirect("/kv/group/list?page=" + WebUtils.findString(request, "page"));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@MenuType(name = "字典分类列表", catalog = true)
	public ModelAndView list(HttpServletRequest request) {
		PageRequest pageRequest = builderPage(request);
		return forward("/kv/group/list", request).addObject("data", kVGroupService.listSlice(pageRequest));
	}

}
