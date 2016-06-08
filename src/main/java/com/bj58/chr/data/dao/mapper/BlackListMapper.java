package com.bj58.chr.data.dao.mapper;

import java.util.Map;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.BlackList;

/**
 * 黑名单mapper
 * @author sunlingao@58.com
 * @date 2016年5月16日
 */
public interface BlackListMapper extends SuperMapper<BlackList>{
    
    BlackList findOneByMap(Map<String,Object> params);
}
