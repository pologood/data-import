package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IKVGroupDao;
import com.bj58.chr.data.dao.mapper.KVGroupMapper;
import com.bj58.chr.data.model.kv.KVGroup;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:22:15
 * @see
 * @since
 */
@Repository
public class KVGroupDao extends SuperDao<KVGroup> implements IKVGroupDao {

	@Resource
	private KVGroupMapper kVGroupMapper;

	@Override
	public SuperMapper<KVGroup> getMapper() {
		return kVGroupMapper;
	}

}
