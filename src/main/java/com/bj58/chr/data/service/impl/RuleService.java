package com.bj58.chr.data.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.IRuleDao;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.Rule;
import com.bj58.chr.data.service.IRuleHandlerService;
import com.bj58.chr.data.service.IRuleService;
import com.google.common.collect.Maps;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午3:59:18
 * @see
 * @since
 */
@Service
public class RuleService extends SuperService<Rule> implements IRuleService {

	@Resource
	private IRuleHandlerService ruleHandlerService;

	@Resource
	private IRuleDao ruleDao;

	@Override
	public ISuperDao<Rule> getSuperDao() {
		return ruleDao;
	}

	@Override
	public Status<String> run(CVencryptCheck cVencryptCheck, int id) {
		Rule rule = ruleDao.findById(id);
		Map<String, Object> object = Maps.newHashMap();
		object.put("cVencryptCheck", cVencryptCheck);
		return ruleHandlerService.handle(object, rule);
	}

}
