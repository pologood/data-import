package com.bj58.chr.data.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.bj58.chr.data.utils.Logger58dp;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.esb.CvOperTypeEnum;
import com.bj58.chr.data.esb.MessageSend;
import com.bj58.chr.data.esb.message.CallBackMessage;
import com.bj58.chr.data.esb.message.CvPostMessage;
import com.bj58.chr.data.handler.SyncHandler;
import com.bj58.chr.data.handler.SyncHandler.UserStatusEnum;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.ICvInfoBoService;
import com.bj58.chr.data.service.ICvStrategyService;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.service.IStatService;
import com.bj58.chr.data.service.ISyncCvService;
import com.bj58.chr.data.utils.NamedThreadFactory;
import com.bj58.chr.data.utils.QDStringUtils;
import com.bj58.chr.data.utils.RedisHeper;

@Service
public class SyncCvService implements ISyncCvService {

    private Logger logger = Logger.getLogger(SyncCvService.class);
    
    private final String KEY = "sync_cv_task";

    /**
     * 同步线程是否开启
     */
    private volatile boolean flag = false;
    private int startIndex = 0;

    /**
     * 休眠时间
     */
    private long intertime = 500L;

    /**
     * 每页数据
     */
    private int limit = 1000;

    @Resource(name = "mongoTemplate")
    private MongoTemplate qdMongoTemplate;

    @Resource(name = "cvsMongoTemplate")
    private MongoTemplate ycMongoTemplate;

    @Resource
    private ICvInfoBoService cvInfoBoService;

    @Resource
    private ISeekerUserService seekerUserService;
    
    @Resource
    private IStatService statService;

    @Resource(name = "syncCvHandler")
    private SyncHandler<CvInfoBo> syncCvHandler;

    @Resource(name = "callBackMessageSend")
    private MessageSend send;

    @Resource(name = "cvMessageSend")
    private MessageSend cvMessageSend;

    @Resource
    private ICvStrategyService cvStrategyService;
    /** 定时任务 */
    private final static ScheduledExecutorService syncTaskService = Executors
            .newSingleThreadScheduledExecutor(new NamedThreadFactory("sync-time-"));

    @Override
    public void queryForListQd(final int skip, final int limit) {
        try {
            Query query = new Query();
            query.skip(skip).limit(limit);
            List<CvInfoBo> list = qdMongoTemplate.find(query, CvInfoBo.class, COLLECTION);
            logger.info(String.format("query is %s for skip is %s and limit is %s",
                    query.toString(), skip, limit));
            if (list != null) {
                saveForYc(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveForYc(final List<CvInfoBo> list) {
        for (CvInfoBo bo : list) {
            try{
            SeekerUser user = seekerUserService.findById(new SeekerUser(bo.getUid(), "", ""));
            bo.setInfoId(user == null ? "" : user.getCoId());
            bo.setVisibility("partly");
            bo.setVerified("1");
            bo.setIsCompleted("1");
            bo.setSrc(10L);
            if (null == bo.getLiving() || StringUtils.isEmpty(bo.getLiving().getCityId())
                    || !QDStringUtils.isMatcherStr(bo.getMobile(),"\\d{11}") || getCvInFoBo(bo)) {
                logger.info(String.format("cvid is %s continue case is living is null or cityId is null or mobile is null or online exist", bo.getId()));
                continue;
            }
            setDefaultTime(bo);
            setWorkTime(bo);
            syncCvForYc(bo);
            TimeUnit.MILLISECONDS.sleep(50);
            }catch(Exception e){
                logger.error("===saveForYc===", e);
            }
        }
    }

    @Override
    public void syncCvForYc(CvInfoBo bo) {
        UserStatusEnum status = syncCvHandler.getUserStatus(bo);
        if (status == null)
            return;
        /** 没有找到相同手机号 */
        if (status.getCode() == UserStatusEnum.NULL.getCode()) {
            /** 绑定新用户 */
            cvStrategyService.bundUser(bo);
        }
        /** 用户已过期 解绑老用户.新增用户,简历 */
        else if (status.getCode() == UserStatusEnum.ALLXP.getCode()) {
            cvStrategyService.unBundUser(bo, status.getUid());
            /** 老账号新增简历 */
        } else {
            if (null != syncCvHandler.update(bo)) {
                bo.setUid(status.getUid());
                ycMongoTemplate.save(bo, COLLECTION);
                logger.info(String.format("addcv uid is %s cvid is %s", bo.getUid(), bo.getId()));
                /** 58dp logger **/
                Logger58dp.getInstance().cvLogger(bo,"C","");

                /** 运营消息 */
                SeekerUser user = seekerUserService.findById(new SeekerUser(bo.getUid(), "", ""));
                if(user !=null && !StringUtils.isEmpty(user.getPwd())){
                send.send(new CallBackMessage(bo, user.getPwd()));
                logger.info(String.format("send callBack msg cvid is %s uid is %s", bo.getId(),bo.getUid()));
                }
                /**
                 * 简历索引发送更新请求
                 */
                cvMessageSend.send(new CvPostMessage(bo.getId(), String.valueOf(bo.getUid()),
                        JSONUtils.writeValue(bo), "update", CvOperTypeEnum.UPDATECONTENT.getId(),
                        ""));
            }
        }
    }
    
    @Deprecated
    public void run() {
//        flag = true;
//        final int total = 2506000;
//        for (int i = getStartIndex(); i < total; i = i + getLimit()) {
//            queryForListQd(i, getLimit());
//            setStartIndex(i);
//            try {
//                Thread.sleep(getIntertime());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void startTask() {
        syncTaskService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {// 每1小时执行一次
                logger.info("run....startSyncCv");
                flag = true;
                startSyncCv();
            }
        }, 0, 2, TimeUnit.HOURS);
    }

    public void startSyncCv() {
        final String keyStr = "lModTime";
        long date =
                NumberUtils.toLong(RedisHeper.getRedisHeper().get(KEY),
                        QDStringUtils.getCvTime(DateUtils.addHours(Calendar.getInstance().getTime(), -12).getTime()));
        int count = cvInfoBoService.getCvCountByTime(keyStr, date);
        for (int i = getStartIndex(); i <= count; i = i + getLimit()) {
            List<CvInfoBo> list =
                    cvInfoBoService.getCvCountByTimePageList(keyStr, date, Direction.ASC, i,
                            getLimit());
            if(!CollectionUtils.isEmpty(list)){
            try {
            saveForYc(list);
            RedisHeper.getRedisHeper().set(KEY, list.get(list.size()-1).getLModTime());
            if(getIntertime()>0) Thread.sleep(getIntertime());
            } catch (Exception e) {
                logger.error("===startSyncCv===", e);
            }
            }
        }
        logger.info(String.format(
                "==== startSyncCv start time is %s end time is %s,count is %s ===", date, NumberUtils.toLong(RedisHeper.getRedisHeper().get(KEY),0),count));
        statService.importSync(count);
    }

    public boolean getStart() {
        return flag;
    }

    public void stop() {
        flag = false;
    }

    public long getIntertime() {
        return intertime;
    }

    public void setIntertime(long intertime) {
        this.intertime = intertime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 0)
            this.limit = limit;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 设置默认时间
     * 
     * @author:sunlingao@58.com
     * @date:2016年4月27日
     * @param bo void
     */
    private void setDefaultTime(CvInfoBo bo) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, -1);
        if (bo.getAddTime() <= 0) {
            bo.setAddTime(QDStringUtils.getCvTime(ca.getTimeInMillis()));
        }
        if (bo.getLModTime() <= 0) {
            bo.setLModTime(QDStringUtils.getCvTime(ca.getTimeInMillis()));
        }
        if (bo.getLRefTime() <= 0) {
            bo.setLRefTime(QDStringUtils.getCvTime(ca.getTimeInMillis()));
        }
    }

    /**
     * 根据工作时间算出参加工作时间
     * 
     * @author:sunlingao@58.com
     * @date:2016年4月28日
     * @param bo
     * @return long
     */
    private void setWorkTime(CvInfoBo bo) {
        if (bo.getWorkTime() <= 0) {
            if (!CollectionUtils.isEmpty(bo.getExperience())) {
                long workTime = bo.getExperience().get(bo.getExperience().size() - 1).getStart();
                bo.setWorkTime(workTime);
                bo.setIsWork(1);
            }
        } else {
            bo.setIsWork(1);
        }
    }

    /**
     * 获取简历实体
     * 
     * @author:sunlingao@58.com
     * @date:2016年4月29日
     * @param id
     * @return String
     */
    private boolean getCvInFoBo(CvInfoBo bo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(bo.getId()));
        boolean flag = ycMongoTemplate.exists(query, COLLECTION);
        if (flag) {
            setWorkTime(bo);
            ycMongoTemplate.save(bo, COLLECTION);
            logger.info(String.format("cvid is %s exist and send update msg", bo.getId()));
            /** 58dp logger **/
            Logger58dp.getInstance().cvLogger(bo,"U","");

            /**
             * 简历索引发送更新请求
             */
            cvMessageSend.send(new CvPostMessage(bo.getId(), String.valueOf(bo.getUid()), JSONUtils
                    .writeValue(bo), "update", CvOperTypeEnum.UPDATECONTENT.getId(), ""));
        }
        return flag;
    }

}
