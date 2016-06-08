package com.bj58.chr.data.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IBlackListDao;
import com.bj58.chr.data.dao.mapper.BlackListMapper;
import com.bj58.chr.data.model.BlackList;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年5月16日
 */
@Repository
public class BlackListDao extends SuperDao<BlackList> implements IBlackListDao {
    
    @Resource
    private BlackListMapper blackListMapper;
    @Override
    public BlackList findOneByMap(Map<String,Object> params) {      
       return blackListMapper.findOneByMap(params);
    }

    @Override
    public SuperMapper<BlackList> getMapper() {
        return blackListMapper;
    }
    
}
