package com.bj58.chr.data.service;

import java.io.Serializable;
import java.util.Map;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.SeekerUser;

public interface ISeekerUserService extends ISuperService<SeekerUser>{
    
    /**
     * 
     * @author:sunlingao@58.com
     * @desc:根据合作id查找用户信息
     * @date:2016年4月11日
     * @param coId
     * @return
     * SeekerUser
     */
    SeekerUser findByCoId(Serializable coId);
    
    public void updateSeekersLink(String uid, String email, String mobile);
    
    public Map getUserIsExistForMobile(String mobile, String coid);
    
    /**
     * 获取用户联系方式
     * @author:sunlingao@58.com
     * @date:2016年5月6日
     * @param uid
     * @return
     * Map
     */
    public Map<String,String> getUserInfo(String uid);
    
}
