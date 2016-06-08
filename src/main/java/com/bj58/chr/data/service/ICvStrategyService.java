package com.bj58.chr.data.service;

import com.bj58.chr.data.model.yccv.CvInfoBo;

/**
 * 简历策略
 * @author sunlingao@58.com
 * @date 2016年5月4日
 */
public interface ICvStrategyService {
    
    public final String COLLECTION="cvInfoBo";
    
    /**
     * 老账号解绑 (新建此账号,简历)
     * @author:sunlingao@58.com
     * @date:2016年5月3日
     * @param bo 简历实体
     * @param oldUid 老英才uid
     * void
     */
    public void unBundUser(CvInfoBo bo,String oldUid);
    
    /**
     * 绑定新用户,新建简历
     * @author:sunlingao@58.com
     * @date:2016年5月4日
     * @param bo
     * void
     */
    public void bundUser(CvInfoBo bo);

}
