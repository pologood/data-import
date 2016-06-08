package com.bj58.chr.data.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.data.model.BlackList;
import com.bj58.chr.data.service.IBlackListService;

/**
 * 黑名单添加控制器
 * 
 * @author sunlingao@58.com
 * @date 2016年5月18日
 */
@Controller
@MenuType(name = "黑名单管理", index = 100)
@RequestMapping("/black")
public class BlackListController extends SuperController {

    @Resource
    private IBlackListService blackListService;

    @RequestMapping("/add")
    @MenuType(name = "添加黑名单", catalog = true)
    public ModelAndView add(@ModelAttribute BlackList bList, HttpServletRequest req) {
        if (WebUtils.isPOST(req)) {
            if (blackListService.findByMobile(bList.getMobile()) == null) {
                blackListService.save(bList);
            }
            return redirect("/black/list?page=1");
        } else {
            return forward("/black/add");
        }
    }
    
    @RequestMapping("/list")
    @MenuType(name = "黑名单列表", catalog = true)
    public ModelAndView list(@RequestParam(value="page",defaultValue="1") int page) {
        return forward("/black/list").addObject("list", blackListService.getListByPage(page, getPageSize()));
    }
    
    @RequestMapping("/remove")
    public ModelAndView del(@RequestParam(value="id",defaultValue="0") int id) {
        if(id > 0) blackListService.remove(id);
        return redirect("/black/list?page=1");
    }
}
