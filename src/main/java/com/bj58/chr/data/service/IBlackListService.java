package com.bj58.chr.data.service;

import java.util.List;

import com.bj58.chr.data.model.BlackList;


/**
 * 
 * @author sunlingao@58.com
 * @date 2016年5月16日
 */
public interface IBlackListService {
    
    /**
     * 根据手机号查找黑名单
     * @author:sunlingao@58.com
     * @date:2016年5月18日
     * @param mobile
     * @return
     * BlackList
     */
    public BlackList findByMobile(String mobile);
    
    /**
     * 保存黑名单
     * @author:sunlingao@58.com
     * @date:2016年5月18日
     * @param bList
     * void
     */
    public void save(BlackList bList);
    
    /**
     * 判断合作id是否存在黑名单中
     * @author:sunlingao@58.com
     * @date:2016年5月21日
     * @param coid
     * @return
     * boolean
     */
    public boolean isExist(String coid);
    
    /**
     * 分页查询
     * @author:sunlingao@58.com
     * @date:2016年5月20日
     * @param page
     * @param pageSize
     * @return
     * List<BlackList>
     */
    public List<BlackList> getListByPage(int page,int pageSize);
    
    /**
     * 删除黑名单
     * @author:sunlingao@58.com
     * @date:2016年5月21日
     * @param id
     * void
     */
    public void remove(int id);

}
