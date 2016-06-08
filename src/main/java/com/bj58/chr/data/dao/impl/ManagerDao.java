package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IManagerDao;
import com.bj58.chr.data.dao.mapper.ManagerMapper;
import com.bj58.chr.data.model.Manager;

@Repository
public class ManagerDao extends SuperDao<Manager> implements IManagerDao {

	@Resource
	private ManagerMapper managerMapper;

	@Override
	public SuperMapper<Manager> getMapper() {
		return managerMapper;
	}

	@Override
	public Manager login(Manager manager) {
		return managerMapper.login(manager);
	}

}
