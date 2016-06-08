package com.bj58.chr.data.dao;

import java.util.Date;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.CVContract;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午4:14:35
 * @see
 * @since
 */
public interface IContractDao extends ISuperDao<CVContract> {

	CVContract findByCoId(String coid);

	/**
	 * @param uid
	 * @param date
	 */
	void update(String uid, Date date);

	/**
	 * @param coid
	 * @param uid
	 */
	void updateUid(String coid, String uid);
	
	/**
	 * 查询uid信息
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
