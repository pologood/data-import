package com.bj58.chr.data.dao.mapper;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.kv.KVEntity;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:24:32
 * @see
 * @since
 */
public interface KVEntityMapper extends SuperMapper<KVEntity> {

	/**
	 * @param t
	 * @return
	 */
	KVEntity findByIdName(KVEntity t);
}
