package com.bj58.chr.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.yccv.CvInfoBo;

/**
 * mongo简历操作
 * @author sunlingao@58.com
 * @date 2016年4月7日
 */
public interface ICvInfoBoDao extends ISuperDao<CvInfoBo>  {
    
    public final String TABLE="cvInfoBo";
    
    /**
     * 保存或更新
     * @author:sunlingao@58.com
     * @date:2016年4月29日
     * @param bo
     * @return
     * Serializable
     */
    public Serializable saveOrUpdate(CvInfoBo bo);
    
    /**
     * 更新多个字段
     * @author:sunlingao@58.com
     * @date:2016年4月29日
     * @param id
     * @param params
     * void
     */
    public void updateByField(String id,Map<String,Object> params);
    
    /**
     * 获取用户简历数量
     * @author:sunlingao@58.com
     * @date:2016年4月29日
     * @param uid
     * @return
     * int
     */
    public int getCvCountForUid(String uid);
    
    /**
     * 获取用户所有未删除简历ids
     * @author:sunlingao@58.com
     * @date:2016年4月29日
     * @param uid
     * @return
     * List<String>
     */
    public List<String> getCvidsForUid(String uid);
    
    /**
     * 根据时间帅选 获取时间字段
     * @author:sunlingao@58.com
     * @date:2016年5月5日
     * @param key
     * @param date
     * @param sort
     * @return
     * int
     */
    public int getCvCountByTime(String key,long date);
    
    /**
     * 根据时间帅选 获取时间字段 分页查询
     * @author:sunlingao@58.com
     * @date:2016年5月5日
     * @param key
     * @param date
     * @return
     * List<CvInfoBo>
     */
    public List<CvInfoBo> getCvCountByTimePageList(String key,long date,Direction sort,int skip,int limit );
}
