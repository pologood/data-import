package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.esb.CvOperTypeEnum;
import com.bj58.chr.data.esb.MessageSend;
import com.bj58.chr.data.esb.message.CallBackMessage;
import com.bj58.chr.data.esb.message.CvPostMessage;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.ICvStrategyService;
import com.bj58.chr.data.service.ISeekerUserService;

@Service
public class CvStrategyService implements ICvStrategyService{
    
    private final Logger logger = Logger.getLogger(CvStrategyService.class);
    
    @Resource(name = "callBackMessageSend")
    private MessageSend send;

    @Resource(name = "cvMessageSend")
    private MessageSend cvMessageSend;
    
    @Resource(name = "cvsMongoTemplate")
    private MongoTemplate ycMongoTemplate;
    
    @Resource
    private ISeekerUserService seekerUserService;
    
    @Override
    public void unBundUser(CvInfoBo bo,String oldUid) {
        /**解绑老用户*/
        seekerUserService.updateSeekersLink(oldUid, "", "");
        /**更新 新用户*/
        seekerUserService.updateSeekersLink(bo.getUid(), bo.getEmail(), bo.getMobile());
        /**保存简历*/
        ycMongoTemplate.save(bo, COLLECTION);
        /**运营消息*/
        SeekerUser user = seekerUserService.findById(new SeekerUser(bo.getUid(), "", ""));
        if(user != null && !StringUtils.isEmpty(user.getPwd())){
        send.send(new CallBackMessage(bo,user.getPwd()));
        logger.info(String.format("send callBack msg cvid is %s uid is %s", bo.getId(),bo.getUid()));
        }
        /**
         * 简历索引发送更新请求
         */
        cvMessageSend.send(new CvPostMessage(bo.getId(), String.valueOf(bo.getUid()),
                JSONUtils.writeValue(bo), "update", CvOperTypeEnum.UPDATECONTENT.getId(), ""));
        logger.info(String.format("unbundUser oldUid is %s and newUid is %s and cvid is %s", oldUid,bo.getUid(),bo.getId()));
    }

    @Override
    public void bundUser(CvInfoBo bo) {
        ycMongoTemplate.save(bo, COLLECTION);
        seekerUserService.updateSeekersLink(bo.getUid(), bo.getEmail(), bo.getMobile());
        logger.info(String.format("bundUser uid is %s cvid is %s", bo.getUid(),bo.getId()));
        /**运营消息*/
        SeekerUser user = seekerUserService.findById(new SeekerUser(bo.getUid(), "", ""));
        if(user != null && !StringUtils.isEmpty(user.getPwd())){
        send.send(new CallBackMessage(bo,user.getPwd()));
        logger.info(String.format("send callBack msg cvid is %s uid is %s", bo.getId(),bo.getUid()));
        }
        /**
         * 简历索引发送更新请求
         */
        cvMessageSend.send(new CvPostMessage(bo.getId(), String.valueOf(bo.getUid()),
                JSONUtils.writeValue(bo), "update", CvOperTypeEnum.UPDATECONTENT.getId(), ""));
    }

}
