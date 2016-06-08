package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.INeedDao;
import com.bj58.chr.data.model.Need;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.INeedService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午7:03:40
 * @see
 * @since
 */
@Service
public class NeedService extends SuperService<Need> implements INeedService {

	@Resource
	private INeedDao needDao;

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Override
	public ISuperDao<Need> getSuperDao() {
		return needDao;
	}

	@Override
	public void fulfil(String coid) {
		needDao.fulfil(coid);
	}

	@Override
	public void fulfilAll(String coid) {
		try {
			fulfil(coid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		asyncTaskService.updateContractImpl(coid);
	}

}
