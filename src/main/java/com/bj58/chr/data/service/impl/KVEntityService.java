package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.dao.impl.KVEntityDao;
import com.bj58.chr.data.model.kv.KVEntity;
import com.bj58.chr.data.service.IKVEntityService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:28:27
 * @see
 * @since
 */
@Service
public class KVEntityService extends SuperService<KVEntity> implements IKVEntityService {

	@Resource
	private KVEntityDao kVEntityDao;

	@Override
	public ISuperDao<KVEntity> getSuperDao() {
		return kVEntityDao;
	}

	@Override
	public void saveOrUpdate(KVEntity t) {
		KVEntity dbT = kVEntityDao.findByIdName(t);
		if (dbT != null) {
			t.setId(dbT.getId());
			update(t);
		} else {
			save(t);
		}
	}

}
