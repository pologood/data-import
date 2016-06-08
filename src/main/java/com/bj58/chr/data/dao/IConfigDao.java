package com.bj58.chr.data.dao;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.Config;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午2:49:34
 * @see
 * @since
 */
public interface IConfigDao extends ISuperDao<Config> {

	/**
	 * @param name
	 * @return
	 */
	String getByName(String name);
}
