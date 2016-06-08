package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.IRuleDao;
import com.bj58.chr.data.dao.mapper.RuleMapper;
import com.bj58.chr.data.model.Rule;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午3:51:46
 * @see
 * @since
 */
@Repository
public class RuleDao extends SuperDao<Rule> implements IRuleDao {

	@Resource
	private RuleMapper ruleMapper;

	@Override
	public SuperMapper<Rule> getMapper() {
		return ruleMapper;
	}

}
