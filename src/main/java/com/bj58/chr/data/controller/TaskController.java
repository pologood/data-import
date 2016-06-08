package com.bj58.chr.data.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.WebUtils;
import com.bj58.chr.data.service.IAsyncQdSourceService;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.IUpdateData;
import com.bj58.chr.data.service.IUptimeData;
import com.bj58.chr.data.utils.RedisHeper;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午8:30:17
 * @see
 * @since
 */
@Controller
@MenuType(name = "任务管理", index = 100)
@RequestMapping("/task")
public class TaskController extends SuperController {

	private final static Logger LOG = LoggerFactory.getLogger(TaskController.class);

	@Resource
	private IAsyncTaskService asyncTaskService;
	
	@Resource
	private IAsyncQdSourceService asyncQdTaskService;

	@Resource
	private IUpdateData iUpdateData;

	@Resource
	private IUptimeData uptimeData;

	private volatile static boolean isRun = false;

	@RequestMapping(value = "/init")
	@MenuType(name = "初始化任务", catalog = true)
	public ModelAndView init(HttpServletRequest request) {
		if (WebUtils.isPOST(request)) {
			boolean stop = WebUtils.checkbox(request, "stop");
			if (stop) {
				asyncTaskService.stopImportData();
			} else {
				boolean run = WebUtils.checkbox(request, "run");
				if (run && !isRun) {
					asyncTaskService.importData();
				} else {
					Date start = WebUtils.findDate(request, "start");
					if (start != null) {
						RedisHeper.getRedisHeper().setRecent(start.getTime());
					}
				}
			}
		}
		return forward("/task/init").addObject("start", new Date(RedisHeper.getRedisHeper().getRecent()));
	}

	@RequestMapping(value = "/contract")
	@MenuType(name = "获得联系方式", catalog = true)
	public ModelAndView contract(HttpServletRequest request) {
		if (WebUtils.isPOST(request)) {
			int db = WebUtils.findInt(request, "db");
			if (db == 100) {
				for (int i = 0; i < 30; i++) {
					asyncTaskService.updateContractDb(i);
				}
			} else if (db == 99) {
			} else {
				asyncTaskService.updateContractDb(db);
			}
			return redirect("/task/contract");
		}
		return forward("/task/contract").addObject("id", asyncTaskService.getId()).addObject("dbIndex",
				asyncTaskService.getDbIndex());
	}

	@RequestMapping(value = "/update")
	@MenuType(name = "补充联系方式", catalog = true)
	@ResponseBody
	public String getContract(HttpServletRequest request) {
		String file = WebUtils.findString(request, "file");
		try {
			if (StringUtils.isNotEmpty(file)) {
				List<String> values = FileUtils.readLines(new File(file));
				for (String value : values) {
					asyncTaskService.updateContract(value);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@RequestMapping(value = "/fill")
	@MenuType(name = "补充简历", catalog = true)
	@ResponseBody
	public String fill(HttpServletRequest request) {
		String file = WebUtils.findString(request, "file");
		int sleep = WebUtils.findInt(request, "sleep", 0);
		if (StringUtils.isNotEmpty(file)) {
			LOG.info(file + "," + sleep);
			iUpdateData.update(file, sleep);
		}
		return file;
	}

	@RequestMapping(value = "/uptime")
	@MenuType(name = "补充简历", catalog = true)
	@ResponseBody
	public String uptime(HttpServletRequest request) {
		String file = WebUtils.findString(request, "file");
		int sleep = WebUtils.findInt(request, "sleep", 0);
		if (StringUtils.isNotEmpty(file)) {
			LOG.info(file + "," + sleep);
			uptimeData.update(file, sleep);
		}
		return file;
	}
	
	@RequestMapping(value = "/asyncQdSource")
	@ResponseBody
	public String startQdTask(){
	    String msg = "";
	    if(!asyncQdTaskService.isStart()){
	    asyncQdTaskService.startAsync();
	    msg = "task start";
	    }else{
	        msg = "task alreadly exists";
	    }
	    return msg;
	}

}
