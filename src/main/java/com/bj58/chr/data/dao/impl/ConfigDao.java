package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IConfigDao;
import com.bj58.chr.data.dao.mapper.ConfigMapper;
import com.bj58.chr.data.model.Config;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午2:53:16
 * @see
 * @since
 */
@Repository
public class ConfigDao extends SuperDao<Config> implements IConfigDao {

	@Resource
	private ConfigMapper configMapper;

	@Override
	public SuperMapper<Config> getMapper() {
		return configMapper;
	}

	@Override
	public String getByName(String name) {
		return configMapper.getByName(name);
	}

}
