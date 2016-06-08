package com.bj58.chr.data.service;

import java.util.Map;

/**
 * 异步统计繁星项目采集来源
 * @author sunlingao@58.com
 * @date 2016年5月26日
 */
public interface IAsyncQdSourceService {
    
    public final String SYNCKEY="sync_qdsid_task";
    
    public final String LOGGERPATH = "/opt/web/qd_cv/logs/stat/content#time#.log";
    
    public void startAsync();
    
    public boolean isStart();
    
    public void setDate(String date);
    
    /**
     * 获取繁星导入来源数据
     * @author:sunlingao@58.com
     * @date:2016年5月31日
     * @param date 日期 2016-05-25
     * @return
     * Map<String,Object>
     */
    public Map<String,Object> getQdSourceDate(String date);
    
}
