package com.bj58.chr.data.service;

import java.util.Date;

import com.bj58.chr.data.model.CVContract;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午5:06:10
 * @see
 * @since
 */
public interface IAsyncTaskService {

	/**
	 * @param coid
	 */
	CVContract updateContractImpl(String coid);

	/**
	 * @param queryCVInfoTemp
	 */
	void importDataImpl();

	/**
	 * @param coid
	 */
	void updateContract(String coid);

	/**
	 * 
	 */
	void importData();

	/**
	 * @param db
	 */
	void updateContractDb(final int db);

	/**
	 * @return
	 */
	int getId();

	/**
	 * @return
	 */
	int getDbIndex();

	/**
	 * 
	 */
	// void updateContractNotGet();

	/**
	 * @param cvId
	 * @param coid
	 * @param date
	 */
	void updateTimeByCvid(String cvId, String uid, String coid, Date date);

	/**
	 * 
	 */
	void stopImportData();

}
