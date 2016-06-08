package com.bj58.chr.data.service;

import java.util.List;

import com.bj58.chr.data.model.yccv.CvInfoBo;

/**
 * 简历同步业务
 * @author sunlingao@58.com
 * @date 2016年4月25日
 */
public interface ISyncCvService {
    
    public final String COLLECTION="cvInfoBo";
    /**
     * 读取数据
     * @author:sunlingao@58.com
     * @date:2016年4月25日
     * @param limit
     * @param pageSize
     * @return
     * void
     */
    public void queryForListQd(int limit,int pageSize);
    
    /**
     * 保存导入简历至yc
     * @author:sunlingao@58.com
     * @date:2016年4月25日
     * @param list
     * void
     */
    public void saveForYc(List<CvInfoBo> list);
    
    /**
     * 根据策略同步简历
     * @author:sunlingao@58.com
     * @date:2016年5月4日
     * @param bo
     * void
     */
    public void syncCvForYc(CvInfoBo bo);
    
}
