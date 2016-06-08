package com.bj58.chr.data.service;

import com.bj58.chr.common.web.Status;
import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.Rule;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午3:58:14
 * @see
 * @since
 */
public interface IRuleService extends ISuperService<Rule> {

	/**
	 * @param rule
	 * @return
	 */
	public Status<String> run(CVencryptCheck cVencryptCheck, int rule);

}
