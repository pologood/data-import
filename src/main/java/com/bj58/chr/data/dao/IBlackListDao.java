package com.bj58.chr.data.dao;

import java.util.Map;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.BlackList;

public interface IBlackListDao extends ISuperDao<BlackList>{
    
    public BlackList findOneByMap(Map<String,Object> params);
}
