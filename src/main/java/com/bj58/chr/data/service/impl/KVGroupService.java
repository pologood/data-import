package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.IKVGroupDao;
import com.bj58.chr.data.model.kv.KVGroup;
import com.bj58.chr.data.service.IKVGroupService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:19:56
 * @see
 * @since
 */
@Service
public class KVGroupService extends SuperService<KVGroup> implements IKVGroupService {

	@Resource
	private IKVGroupDao kVGroupDao;

	@Override
	public ISuperDao<KVGroup> getSuperDao() {
		return kVGroupDao;
	}

}
