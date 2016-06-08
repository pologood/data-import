package com.bj58.chr.data.dao;

import java.util.List;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.CVencrypt;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:13:18
 * @see
 * @since
 */
public interface ICVencryptDao extends ISuperDao<CVencrypt> {

	/**
	 * @param encrypt
	 * @return
	 */
	List<CVencrypt> findByEncrypt(String encrypt);

}
