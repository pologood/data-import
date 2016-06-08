package com.bj58.chr.data.service;

import java.util.List;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.CVAsk;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:11:12
 * @see
 * @since
 */
public interface ICVencryptService extends ISuperService<CVencrypt> {

	/**
	 * @param cvask
	 * @return
	 */
	CVencryptCheck getCVencrypt(CVAsk cvask, CvIdCoId cvIdCoId);

	/**
	 * @param cVencryptList
	 */
	public void add(final CVencrypt cVencrypt);

	/**
	 * @param mndigest
	 * @return
	 */
	List<CVencrypt> findByEncrypt(String mndigest);

}
