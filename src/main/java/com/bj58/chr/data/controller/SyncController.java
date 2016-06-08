package com.bj58.chr.data.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.data.service.impl.SyncCvService;

@Controller
@MenuType(name = "同步配置", index = 100)
@RequestMapping("/sync")
public class SyncController extends SuperController {

    @Resource
    private SyncCvService service;

    @RequestMapping(value = "/init")
    @MenuType(name = "同步初始", catalog = true)
    public ModelAndView edit(HttpServletRequest request) {
        return forward("sync/init").addObject("isStart", service.getStart())
                .addObject("intertime", service.getIntertime())
                .addObject("limit", service.getLimit()).addObject("startIndex", service.getStartIndex());
    }

    @RequestMapping(value = "/start")
    public ModelAndView start(HttpServletRequest request) {
        service.setIntertime(WebUtils.findInt(request, "intertime"));
        service.setLimit(WebUtils.findInt(request, "limit"));
        service.setStartIndex(WebUtils.findInt(request, "startIndex"));
        if (WebUtils.checkbox(request, "run") && !service.getStart()) {
            service.startTask();
        }
        return edit(request);
    }

    @RequestMapping(value = "/stop")
    public ModelAndView stop(HttpServletRequest request) {
            service.stop();
        return edit(request);
    }
    
}
