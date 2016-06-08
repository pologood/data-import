package com.bj58.chr.data.service;

import java.io.Serializable;

import com.bj58.chr.data.model.CvIdCoId;

/**
 * 用户召唤业务
 * @author sunlingao@58.com
 * @date 2016年4月8日
 */
public interface ISeekersCallBackService {
    
    /**
     * @author:sunlingao@58.com
     * @desc:创建普通用户
     * @date:2016年4月9日
     * @param cvCo
     * @return
     * Serializable
     */
    public Serializable createSeekers(CvIdCoId cvCo);
    
    /**
     * @author:sunlingao@58.com
     * @desc:创建带有邮箱用户
     * @date:2016年4月9日
     * @param email
     * @param cvCo
     * @return
     * Serializable
     */
    public Serializable createSeekersForEmail(String email,CvIdCoId cvCo);
    
    /**
     * @author:sunlingao@58.com
     * @desc:创建带有手机号用户
     * @date:2016年4月9日
     * @param mobile
     * @param cvCo
     * @return
     * Serializable
     */
    public Serializable createSeekersForMobile(String mobile,CvIdCoId cvCo);
    
    /**
     * @author:sunlingao@58.com
     * @desc:更新联系方式
     * @date:2016年4月9日
     * @param uid 用户id
     * @param email 邮箱
     * @param mobile 手机号
     * void
     */
    public void updateSeekersLink(String uid,String email,String mobile);
    
    /**
     * 获取手机号是否存在
     * @author:sunlingao@58.com
     * @date:2016年4月27日
     * @param mobile
     * @param coid
     * @return true 不存在 false 存在
     * boolean
     */
    public boolean getUserIsExistForMobile(String mobile,String coid);
    
}
