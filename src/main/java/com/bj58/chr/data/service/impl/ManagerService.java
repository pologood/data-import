package com.bj58.chr.data.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.CodecUtils;
import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.IManagerDao;
import com.bj58.chr.data.model.Manager;
import com.bj58.chr.data.service.IManagerService;

@Service
public class ManagerService extends SuperService<Manager> implements IManagerService {

	@Resource
	private IManagerDao managerDao;

	@Override
	public ISuperDao<Manager> getSuperDao() {
		return managerDao;
	}

	@Override
	public Manager login(Manager manager) {
		manager.setPassword(CodecUtils.md5(manager.getPassword()));
		return managerDao.login(manager);
	}

	@Override
	public Serializable save(Manager t) {
		t.setPassword(CodecUtils.md5(t.getPassword()));
		return super.save(t);
	}

	@Override
	public void update(Manager t) {
		t.setPassword(CodecUtils.md5(t.getPassword()));
		super.update(t);
	}

}
