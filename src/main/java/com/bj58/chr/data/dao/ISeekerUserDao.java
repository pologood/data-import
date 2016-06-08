package com.bj58.chr.data.dao;

import java.io.Serializable;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.SeekerUser;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年4月9日
 */
public interface ISeekerUserDao extends ISuperDao<SeekerUser>{
    
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

}
