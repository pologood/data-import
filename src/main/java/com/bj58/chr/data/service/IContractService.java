package com.bj58.chr.data.service;

import java.util.Date;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.CVContract;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午4:12:33
 * @see
 * @since
 */
public interface IContractService extends ISuperService<CVContract> {

	/**
	 * @param coid
	 * @return
	 */
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
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年5月16日
	 * @param uid
	 *            用户id
	 * @param status
	 *            用户类型
	 * @param createTime
	 *            用户创建时间 void
	 */
	void saveOrUpdateLoginTime(String uid, String status, String createTime);

	/**
	 * <p>
	 * 用户不存在时 新增用户并更新登录时间,<br>
	 * 用户存在,只刷新登录时间
	 * </p>
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年5月16日
	 * @param contract
	 *            void
	 */
	void saveOrUpdateLoginTimeImpl(CVContract contract);

	/**
	 * 根据手机号查询合作id
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年5月20日
	 * @param mobile
	 * @return String
	 */
	public String findCoidByMobile(String mobile);

	/**
	 * @param uid
	 * @return
	 */
	CVContract findByUid(String uid);

}
