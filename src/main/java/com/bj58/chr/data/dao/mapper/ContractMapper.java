package com.bj58.chr.data.dao.mapper;

import java.util.Map;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.CVContract;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午4:29:22
 * @see
 * @since
 */
public interface ContractMapper extends SuperMapper<CVContract> {

	/**
	 * @param coid
	 * @return
	 */
	CVContract findByCoId(String coid);

	/**
	 * @param map
	 */
	void updateTime(Map<String, Object> map);

	/**
	 * @param map
	 */
	void updateUid(Map<String, Object> map);
	
	/**
	 * 查询用户id
	 * @author:sunlingao@58.com
	 * @date:2016年5月16日
	 * @param uid
	 * @return
	 * CVContract
	 */
	CVContract findByUid(String uid);
	
	/**
	 * 根据手机号查询合作id
	 * @author:sunlingao@58.com
	 * @date:2016年5月20日
	 * @param mobile
	 * @return
	 * String
	 */
	String findCoidByMobile(String mobile);

}
