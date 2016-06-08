package com.bj58.chr.data.service;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.Manager;

public interface IManagerService extends ISuperService<Manager> {

	/**
	 * @param manager
	 * @return
	 */
	Manager login(Manager manager);

}
