package com.bj58.chr.data.service;

import java.util.Map;

import com.bj58.chr.api.scf.entity.company.CompanyInfoBo;
import com.bj58.chr.data.model.wb.User58;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月19日 下午10:02:25
 * @see
 * @since
 */
public interface ICompanyService {

	/**
	 * @param comId
	 * @return
	 */
	CompanyInfoBo findById(String comId);

	/**
	 * @param comBo
	 * @return
	 */
	boolean isEvenSycnJob(CompanyInfoBo comBo);

	String getBuild(CompanyInfoBo comBo);

	/**
	 * @param comId
	 * @return
	 */
	Map<String, String> getCompanyParam(String comId);

	/**
	 * @param comId
	 */
	void setCompanySyncFlag(String comId);

	/**
	 * @param comId
	 * @param user58
	 */
	void updAccessToken(String comId, User58 user58);

}
