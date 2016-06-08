package com.bj58.chr.data.service.impl;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.IContractDao;
import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.model.CVContract.Data;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.utils.QDStringUtils;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午4:13:05
 * @see
 * @since
 */
@Service
public class ContractService extends SuperService<CVContract> implements IContractService {

    private ExecutorService executors = Executors.newFixedThreadPool(10);
    
	@Resource
	private IContractDao contractDao;

	@Override
	public ISuperDao<CVContract> getSuperDao() {
		return contractDao;
	}

	@Override
	public CVContract findByCoId(String coid) {
		return contractDao.findByCoId(coid);
	}

	public void saveOrUpdate(CVContract contract) {
		if (findByCoId(contract.getCoid()) != null) {
			update(contract);
		} else {
			save(contract);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bj58.chr.data.service.IContractService#update(java.lang.String,
	 * java.util.Date)
	 */
	@Override
	public void update(String uid, Date date) {
		contractDao.update(uid, date);
	}

	@Override
	public void updateUid(String coid, String uid) {
		contractDao.updateUid(coid, uid);
	}
	
    @Override
    public void saveOrUpdateLoginTime(String uid,String status,String createTime) {
        final CVContract contract = new CVContract();
        contract.setUid(uid);
        contract.setStatus(NumberUtils.toInt(status, 2));
        contract.setCreatedTime(new Date(NumberUtils.toLong(createTime,0)));
        contract.setLoginTime(new Date());
        contract.setCoid(QDStringUtils.getImportUuid());
        Data data = new Data();
        data.setEmails("");
        data.setNamemobiles("");
        contract.setData(data);
        executors.submit(new Runnable() {
            @Override
            public void run() {
                saveOrUpdateLoginTimeImpl(contract);
            }
        });
    }

    @Override
    public void saveOrUpdateLoginTimeImpl(CVContract contract) {
        if(contract ==null || StringUtils.isEmpty(contract.getUid())) return ;
        if(contractDao.findByUid(contract.getUid()) != null ){
            update(contract.getUid(), contract.getLoginTime());
        }else{
            save(contract);
        }
    }

    @Override
    public String findCoidByMobile(String mobile) {
        return contractDao.findCoidByMobile(mobile);
    }

	/* (non-Javadoc)
	 * @see com.bj58.chr.data.service.IContractService#findByUid(java.lang.String)
	 */
	@Override
	public CVContract findByUid(String uid) {
		return contractDao.findByUid(uid);
	}

}
