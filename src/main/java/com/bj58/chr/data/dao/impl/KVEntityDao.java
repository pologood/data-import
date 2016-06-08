package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IKVEntityDao;
import com.bj58.chr.data.dao.mapper.KVEntityMapper;
import com.bj58.chr.data.model.kv.KVEntity;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:25:40
 * @see
 * @since
 */
@Repository
public class KVEntityDao extends SuperDao<KVEntity> implements IKVEntityDao {

	@Resource
	private KVEntityMapper kVEntityMapper;

	@Override
	public SuperMapper<KVEntity> getMapper() {
		return kVEntityMapper;
	}

	/**
	 * @param t
	 * @return
	 */
	public KVEntity findByIdName(KVEntity t) {
		return kVEntityMapper.findByIdName(t);
	}

}
