package com.bj58.chr.data.dao.impl;


import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.ISeekerUserDao;
import com.bj58.chr.data.dao.mapper.SeekerUserMapper;
import com.bj58.chr.data.model.SeekerUser;

@Repository
public class SeekerUserDao extends SuperDao<SeekerUser> implements ISeekerUserDao{
    
    @Resource
    private SeekerUserMapper seekUserMapper;
    
    @Override
    public SuperMapper<SeekerUser> getMapper() {
        return seekUserMapper;
    }

    @Override
    public SeekerUser findByCoId(Serializable coId) {
        return seekUserMapper.findByCoId(coId);
    }


}
