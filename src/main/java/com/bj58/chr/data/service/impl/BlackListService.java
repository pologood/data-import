package com.bj58.chr.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.dao.IBlackListDao;
import com.bj58.chr.data.model.BlackList;
import com.bj58.chr.data.service.IBlackListService;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.ICvIdCoIdService;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年5月21日
 */
@Service
public class BlackListService implements IBlackListService{
    
    @Resource
    private IBlackListDao blackListDao;
    
    @Resource
    private ICvIdCoIdService cvidCoidService;
    
    @Resource
    private IContractService contractService;
    
    @Override
    public void save(BlackList bList) {
        /**find coid*/
        String coid = contractService.findCoidByMobile(bList.getMobile());
        if(StringUtils.isNotEmpty(coid)){
            bList.setCoid(coid);
            bList.setCvid("");
            blackListDao.save(bList);
        }
    }

    @Override
    public boolean isExist(String coid) {
        return blackListDao.findOneByMap(ParamUtils.createParam().add("coid", coid).get())==null?false:true;
    }

    @Override
    public BlackList findByMobile(String mobile) {
        return blackListDao.findOneByMap(ParamUtils.createParam().add("mobile", mobile).get());
    }

    @Override
    public List<BlackList> getListByPage(int page, int pageSize) {
        int offset = (page-1>0?page-1:0) *pageSize;
        return blackListDao.list(ParamUtils.createParam().add("offset", offset).add("pageSize", pageSize).get());
    }

    @Override
    public void remove(int id) {
        blackListDao.remove(id);
    }

}
