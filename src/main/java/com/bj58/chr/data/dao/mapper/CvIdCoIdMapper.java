package com.bj58.chr.data.dao.mapper;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.interceptor.core.Shard;
import com.bj58.chr.data.interceptor.core.ShardType;
import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:15:48
 * @see
 * @since
 */
@Shard(shardType = ShardType.HASH, args = "coId", join = "_", part = 30)
public interface CvIdCoIdMapper extends SuperMapper<CvIdCoId> {

}
