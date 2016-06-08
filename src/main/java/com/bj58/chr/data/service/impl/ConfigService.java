package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.IConfigDao;
import com.bj58.chr.data.model.Config;
import com.bj58.chr.data.service.IConfigService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午2:56:41
 * @see
 * @since
 */
@Service
public class ConfigService extends SuperService<Config> implements IConfigService {

	@Resource
	private IConfigDao configDao;

	@Override
	public ISuperDao<Config> getSuperDao() {
		return configDao;
	}

	@Override
	public String getByName(String name) {
		String value = configDao.getByName(name);
		if (StringUtils.isEmpty(value)) {
			value = StringUtils.EMPTY;
		}
		return value;
	}
}
