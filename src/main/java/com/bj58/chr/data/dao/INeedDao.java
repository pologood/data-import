package com.bj58.chr.data.dao;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.Need;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date  2016年3月31日 下午7:04:33
 * @see 
 * @since  
 */
public interface INeedDao extends ISuperDao<Need> {

	/**
	 * @param coid
	 */
	void fulfil(String coid);

}
