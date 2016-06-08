package com.bj58.chr.data.service;

import java.util.HashMap;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月12日 下午6:07:55
 * @see
 * @since
 */
public interface ICheckSerivce {

	/**
	 * @param file
	 */
	void checkNew(String file);

	/**
	 * @param coId
	 */
	HashMap<Object, Object> checkCoId(String coId);

}
