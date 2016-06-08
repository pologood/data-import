package com.bj58.chr.data.service;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.Need;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午6:57:31
 * @see
 * @since
 */
public interface INeedService extends ISuperService<Need> {

	/**
	 * @param coid
	 */
	void fulfil(String coid);

	/**
	 * @param coid
	 */
	void fulfilAll(String coid);

}
