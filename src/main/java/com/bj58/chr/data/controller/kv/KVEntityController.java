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
import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.controller.SuperController;
import com.bj58.chr.data.model.kv.KVEntity;
import com.bj58.chr.data.service.IKVEntityService;
import com.bj58.chr.data.service.IKVGroupService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:53:01
 * @see
 * @since
 */
@Controller
@MenuType(name = "字典表管理", index = 2)
@RequestMapping("/kv/entity")
public class KVEntityController extends SuperController {

	@Resource
	private IKVEntityService kVEntityService;

	@Resource
	private IKVGroupService kVGroupService;

	@RequestMapping(value = "/add")
	@MenuType(name = "添加字典")
	public ModelAndView add(@ModelAttribute("kVEntity") KVEntity kVEntity, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = null;
		if (WebUtils.isGET(request)) {
			kVEntity.setkVGroup(kVGroupService.findById(kVEntity.getkVGroup().getId()));
			modelAndView = forward("/kv/entity/add");
		} else {
			kVEntityService.saveOrUpdate(kVEntity);
			modelAndView = redirect("/kv/entity/list", request, "kVGroup.id");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/edit")
	@MenuType(name = "字典修改")
	public ModelAndView edit(HttpServletRequest request) {
		int id = WebUtils.findInt(request, "id");
		KVEntity kVEntity = kVEntityService.findById(id);
		kVEntity.setkVGroup(kVGroupService.findById(kVEntity.getkVGroup().getId()));
		ModelAndView modelAndView = forward("kv/entity/add");
		modelAndView.addObject("kVEntity", kVEntity);
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@MenuType(name = "删除字典")
	public ModelAndView delete(HttpServletRequest request) {
		int id = WebUtils.findInt(request, "id");
		kVEntityService.remove(id);
		return redirect("/kv/entity/list?page=" + WebUtils.findString(request, "page"));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@MenuType(name = "字典列表")
	public ModelAndView list(HttpServletRequest request) {
		PageRequest pageRequest = builderPage(request);
		int kVGroupId = WebUtils.findInt(request, "kVGroup.id");
		return forward("/kv/entity/list", request).addObject("data",
				kVEntityService.listSlice(ParamUtils.createParam().add("kVGroupId", kVGroupId).get(), pageRequest));
	}

}
