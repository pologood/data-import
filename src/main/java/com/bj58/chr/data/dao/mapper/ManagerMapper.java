package com.bj58.chr.data.dao.mapper;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.Manager;

/**
 * @description: TODO
 * @author : minds
 * @date : 2015年10月21日 上午11:41:24
 */
public interface ManagerMapper extends SuperMapper<Manager> {

	/**
	 * @param manager
	 * @return
	 */
	Manager login(Manager manager);

}
