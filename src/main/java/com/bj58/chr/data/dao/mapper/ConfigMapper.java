package com.bj58.chr.data.dao.mapper;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.Config;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午2:48:33
 * @see
 * @since
 */
public interface ConfigMapper extends SuperMapper<Config> {

	String getByName(String name);

}
