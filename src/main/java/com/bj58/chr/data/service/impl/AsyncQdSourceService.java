package com.bj58.chr.data.service.impl;


import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Response;

import com.bj58.chr.common.ConstantUtils;
import com.bj58.chr.common.MtDateUtils;
import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.model.qdcv.DeliverInfo;
import com.bj58.chr.data.service.IAsyncQdSourceService;
import com.bj58.chr.data.utils.JSONUtils;
import com.bj58.chr.data.utils.RedisHeper;
import com.google.common.collect.Maps;

/**
 * 定时任务跑前一天的巧达导入来源统计
 * 
 * @author sunlingao@58.com
 * @date 2016年5月26日
 */
@Service
public class AsyncQdSourceService extends FileReadService<String> implements IAsyncQdSourceService {
    
    private Logger logger = LoggerFactory.getLogger(AsyncQdSourceService.class);
    private ScheduledExecutorService executors = Executors.newScheduledThreadPool(1);
    private final String REDISKEY = "sync_qdsid_#date#_#sid#";
    
    private String globalDate;

    private volatile boolean flag = false;

    public String getGlobalDate() {
        return globalDate;
    }

    public void setGlobalDate(String globalDate) {
        this.globalDate = globalDate;
    }

    private Calendar calendar = Calendar.getInstance();

    @Override
    public void startAsync() {
        if (!flag) {
            flag = true;
            executors.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    asyncQdSource();
                }
                 }, 0, 24, TimeUnit.HOURS);
        }
    }

    private void asyncQdSource() {
        String date = MtDateUtils.formatYMD(MtDateUtils.prefDate(calendar.getTime()));
        String path = LOGGERPATH.replace("#time#", globalDate == null ? date : globalDate);
        logger.info(String.format("start path is %s", path));
        update(path, 0);
        logger.info("finish == "+ path);
    }

    @Override
    public void handlerFile(String content) {
        String date =
                globalDate == null ? MtDateUtils.formatYMD(MtDateUtils.prefDate(calendar.getTime())) : globalDate;
        /** convert json */
        Map map = null;
        try {
            map = JSONUtils.readValue(content, Map.class);
        } catch (Exception e1) {}
        if (map != null)
            try {
                String debase64 = URLDecoder.decode(String.valueOf(map.get("content")),
                                ConstantUtils.CHARASET);
                debase64 = new String(Base64.decodeBase64(debase64.replaceAll("\\s", "+")),
                                ConstantUtils.CHARASET);
                CVModel model = JSONUtils.readValue(debase64, CVModel.class);
                if (model != null && CollectionUtils.isNotEmpty(model.getDeliverInfo())) {
                    DeliverInfo deliver = model.getDeliverInfo().get(0);
                    String key =
                            REDISKEY.replace("#date#", date).replace("#sid#", deliver.getSid());
                    RedisHeper.getRedisHeper().incr(key);
                }
            } catch (Exception e) {
                logger.error("",e);
            }
    }
    
    public boolean isStart(){
        return flag;
    }

    @Override
    public void setDate(String date) {
        this.globalDate = date;
    }

    @Override
    public Map<String, Object> getQdSourceDate(String date) {
        /** 1:智联招聘 2:前程无忧 3:猎聘网 4:中华英才 5:58同城 6:赶集网 7:拉钩网 8:周伯通 9:若邻网 */
        final String[] SOURCES = {"1","2","3","4","5","6","7","8","9"};
        final String[] KEYS = new String[SOURCES.length];
        for(int i=0;i<SOURCES.length;i++){
            String str = SOURCES[i];
            KEYS[i] = REDISKEY.replace("#date#", date).replace("#sid#", str);
        }
        Map<String,Response<String>> valueMap = RedisHeper.getRedisHeper().getIncr(KEYS);
        Map<String,Object> map = Maps.newHashMap();
        for(int i=0;i<KEYS.length;i++){
            map.put("sync_qdsid_"+SOURCES[i], valueMap.get(KEYS[i]).get());
        }
        return map;
    }
    
    

}
