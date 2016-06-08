package com.bj58.chr.data.dao.mapper;

import java.util.List;
import java.util.Map;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.interceptor.core.Shard;
import com.bj58.chr.data.interceptor.core.ShardType;
import com.bj58.chr.data.model.CVencrypt;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:14:56
 * @see
 * @since
 */
@Shard(shardType = ShardType.HASH, args = "encrpt", join = "", part = 100)
public interface CVencryptMapper extends SuperMapper<CVencrypt> {

	/**
	 * @param encrypt
	 * @return
	 */
	List<CVencrypt> findByEncrypt(Map<String, Object> encrypt);

}
