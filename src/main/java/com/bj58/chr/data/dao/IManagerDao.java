package com.bj58.chr.data.dao;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.Manager;

public interface IManagerDao extends ISuperDao<Manager> {

	/**
	 * @param manager
	 * @return
	 */
	Manager login(Manager manager);

}
