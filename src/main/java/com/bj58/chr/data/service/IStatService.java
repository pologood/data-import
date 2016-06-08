package com.bj58.chr.data.service;

import java.util.Map;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月9日 下午2:18:55
 * @see
 * @since
 */
public interface IStatService {

	/**
	 * @param day
	 * @return
	 */
	String importOldError(int day);

	/**
	 * @return
	 */
	long importOldError();

	/**
	 * @return
	 */
	long importNewError();

	/**
	 * @param day
	 * @return
	 */
	String importNewError(int day);

	/**
	 * @param day
	 * @return
	 */
	String importOld(int day);

	/**
	 * @return
	 */
	long importOld();

	/**
	 * @param day
	 * @return
	 */
	String importNew(int day);

	/**
	 * @return
	 */
	long importNew();

	/**
	 * @param day
	 * @return
	 */
	String askOldError(int day);

	/**
	 * @return
	 */
	long askOldError();

	/**
	 * @param day
	 * @return
	 */
	String askOld(int day);

	/**
	 * @return
	 */
	long askOld();

	/**
	 * @param day
	 * @return
	 */
	String askNewError(int day);

	/**
	 * @param day
	 * @return
	 */
	String askNew(int day);

	/**
	 * @return
	 */
	long askNewError();

	/**
	 * @param day
	 * @return
	 */
	String askAll(int day);

	/**
	 * @return
	 */
	long askNew();

	/**
	 * @return
	 */
	long askAll();

	/**
	 * @param day
	 * @param time
	 * @return
	 */
	Map<String, Object> getALLData(int day, String time);

	/**
	 * @return
	 */
	long importjxError();

	/**
	 * @param day
	 * @return
	 */
	String importjxError(int day);

	/**
	 * @return
	 */
	long askReject();

	/**
	 * @param day
	 * @return
	 */
	String askReject(int day);

	/**
	 * @return
	 */
	long contract();

	/**
	 * @param day
	 * @return
	 */
	String contract(int day);

	/**
	 * @return
	 */
	long contractError();

	/**
	 * @param day
	 * @return
	 */
	String contractError(int day);

	/**
	 * @return
	 */
	long askNewFull();

	/**
	 * @param day
	 * @return
	 */
	String askNewFull(int day);

	/**
	 * @return
	 */
	long askNewM();

	/**
	 * @param day
	 * @return
	 */
	String askNewM(int day);

	/**
	 * @return
	 */
	long askNewNo();

	/**
	 * @param day
	 * @return
	 */
	String askNewNo(int day);

	/**
	 * @return
	 */
	long askOldFull();

	/**
	 * @param day
	 * @return
	 */
	String askOldFull(int day);

	/**
	 * @return
	 */
	long askOldM();

	/**
	 * @param day
	 * @return
	 */
	String askOldM(int day);

	/**
	 * @return
	 */
	long askOldNo();

	/**
	 * @param day
	 * @return
	 */
	String askOldNo(int day);

	/**
	 * @return
	 */
	long askNewReject();

	/**
	 * @param day
	 * @return
	 */
	String askNewReject(int day);

	/**
	 * @return
	 */
	long askOldReject();

	/**
	 * @param day
	 * @return
	 */
	String askOldReject(int day);
	
	/**
	 * 每日同步数据
	 * @author:sunlingao@58.com
	 * @date:2016年5月30日
	 * @param num
	 * @return
	 */
	void importSync(int num);
	
	/**
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年5月30日
	 * @param day
	 * @return
	 * String
	 */
	String askImportSync(int day);
}
