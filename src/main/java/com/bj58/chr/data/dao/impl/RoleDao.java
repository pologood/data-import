package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IRoleDao;
import com.bj58.chr.data.dao.mapper.RoleMapper;
import com.bj58.chr.data.model.Role;

@Repository
public class RoleDao extends SuperDao<Role> implements IRoleDao {

	@Resource
	private RoleMapper roleMapper;

	@Override
	public SuperMapper<Role> getMapper() {
		return roleMapper;
	}

}
