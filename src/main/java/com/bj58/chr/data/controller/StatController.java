package com.bj58.chr.data.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bj58.chr.auth.MenuType;
import com.bj58.chr.common.MtDateUtils;
import com.bj58.chr.data.model.Stat;
import com.bj58.chr.data.service.IAsyncQdSourceService;
import com.bj58.chr.data.service.IStatService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月9日 下午3:54:30
 * @see
 * @since
 */
@Controller
@MenuType(name = "统计中心", index = 30)
@RequestMapping("/stat")
public class StatController extends SuperController {
    
    /**统计时间维度*/
    private final int STATDAY = 15;
    
	@Resource
	private IStatService statService;

	@Resource
	private MongoTemplate mongoTemplate;
	
	@Resource
	private IAsyncQdSourceService asyncQdService;

	@RequestMapping(value = "/show")
	@MenuType(name = "日常统计", catalog = true)
	public ModelAndView show(HttpServletRequest request) {
		List<Map<String, Object>> list = Lists.newArrayList();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < STATDAY; i++) {
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			list.add(statService.getALLData(day, MtDateUtils.formatYMD(calendar.getTime())));
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		return forward("/stat/show").addObject("data", list);
	}

	@RequestMapping(value = "/permit")
	@MenuType(name = "数据落地")
	public String permit(HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < STATDAY; i++) {
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			String id = MtDateUtils.formatYMD(calendar.getTime());
			Stat stat = new Stat();
			stat.setId(id);
			stat.setMap(statService.getALLData(day, id));
			mongoTemplate.save(stat);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		return "ok";
	}
	
	@RequestMapping(value = "/syncStat")
	@MenuType(name = "离线同步统计", catalog = true)
	public ModelAndView sync(HttpServletRequest request){
	    List<Map<String, Object>> list = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < STATDAY; i++) {
            String date = MtDateUtils.formatYMD(calendar.getTime());
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            Map<String,Object> map = Maps.newHashMap();
            map.put("date", date);
            map.put("import_sync", statService.askImportSync(day));
            map.putAll(asyncQdService.getQdSourceDate(date));
            list.add(map);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        return forward("/stat/syncShow").addObject("data", list);
	}

}
