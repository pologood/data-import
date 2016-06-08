package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.IRoleDao;
import com.bj58.chr.data.model.Role;
import com.bj58.chr.data.service.IRoleService;

@Service
public class RoleService extends SuperService<Role> implements IRoleService {

	@Resource
	private IRoleDao roleDao;

	@Override
	public ISuperDao<Role> getSuperDao() {
		return roleDao;
	}

}
