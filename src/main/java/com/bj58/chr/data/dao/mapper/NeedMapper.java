package com.bj58.chr.data.dao.mapper;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.Need;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午7:05:45
 * @see
 * @since
 */
public interface NeedMapper extends SuperMapper<Need> {

	/**
	 * @param coid
	 */
	void fulfil(String coid);

}
