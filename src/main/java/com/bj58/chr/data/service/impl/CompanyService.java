package com.bj58.chr.data.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bj58.chr.api.scf.entity.company.CompanyInfoBo;
import com.bj58.chr.data.model.wb.User58;
import com.bj58.chr.data.service.ICompanyService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月19日 下午10:02:35
 * @see
 * @since
 */
@Service
public class CompanyService implements ICompanyService {

	@Override
	public CompanyInfoBo findById(String comId) {
		return null;
	}

	@Override
	public boolean isEvenSycnJob(CompanyInfoBo comBo) {
		return false;
	}

	@Override
	public String getBuild(CompanyInfoBo comBo) {
		String buid = comBo.getUid();
		if (StringUtils.isEmpty(buid)) {
			long buid_ = comBo.getBuid();
			if (buid_ > 0) {
				buid = String.valueOf(comBo.getBuid());
			}
		}
		return buid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bj58.chr.data.service.ICompanyService#getCompanyParam(java.lang.
	 * String)
	 */
	@Override
	public Map<String, String> getCompanyParam(String comId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bj58.chr.data.service.ICompanyService#setCompanySyncFlag(java.lang.
	 * String)
	 */
	@Override
	public void setCompanySyncFlag(String comId) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bj58.chr.data.service.ICompanyService#updAccessToken(java.lang.
	 * String, com.bj58.chr.data.model.wb.User58)
	 */
	@Override
	public void updAccessToken(String comId, User58 user58) {
		// TODO Auto-generated method stub

	}

}
