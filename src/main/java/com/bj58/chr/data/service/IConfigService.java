package com.bj58.chr.data.service;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.Config;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午2:56:12
 * @see
 * @since
 */
public interface IConfigService extends ISuperService<Config> {

	/**
	 * @param updateContact
	 * @return
	 */
	String getByName(String updateContact);

}
