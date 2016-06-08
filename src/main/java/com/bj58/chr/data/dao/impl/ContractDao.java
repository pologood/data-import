package com.bj58.chr.data.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.dao.IContractDao;
import com.bj58.chr.data.dao.mapper.ContractMapper;
import com.bj58.chr.data.model.CVContract;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午4:15:02
 * @see
 * @since
 */
@Repository
public class ContractDao extends SuperDao<CVContract> implements IContractDao {

	@Resource
	private ContractMapper contractMapper;

	@Override
	public SuperMapper<CVContract> getMapper() {
		return contractMapper;
	}

	@Override
	public CVContract findByCoId(String coid) {
		return contractMapper.findByCoId(coid);
	}

	@Override
	public void update(String uid, Date date) {
		contractMapper.updateTime(ParamUtils.createParam().add("uid", uid).add("date", date).get());
	}

	@Override
	public void updateUid(String coid, String uid) {
		contractMapper.updateUid(ParamUtils.createParam().add("coid", coid).add("uid", uid).get());
	}

    @Override
    public CVContract findByUid(String uid) {
        return contractMapper.findByUid(uid);
    }

    @Override
    public String findCoidByMobile(String mobile) {
        return contractMapper.findCoidByMobile(mobile);
    }

}
