package com.bj58.chr.data.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;

import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.yccv.CvInfoBo;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午6:44:42
 * @see
 * @since
 */
public interface ICvInfoBoService {

	/**
	 * @param cvId
	 * @return
	 */
	CvInfoBo findById(String cvId);

	/**
	 * 
	 * @author:sunlingao@58.com
	 * @desc:
	 * @date:2016年4月8日
	 * @param t
	 *            简历实体
	 * @param cvCo
	 *            合作信息
	 * @return Serializable
	 */
	Serializable save(CvInfoBo t, CvIdCoId cvCo);

	/**
	 * 
	 * @author:sunlingao@58.com
	 * @desc:判断简历是否新增 或者更新
	 * @date:2016年4月8日
	 * @param cvInfoBo
	 *            简历实体
	 * @param coid
	 *            合作id
	 * @return Serializable
	 */
	Serializable saveOrUpdate(CvInfoBo cvInfoBo, String coid);

	/**
	 * 
	 * @author:sunlingao@58.com
	 * @desc:更新多个字段
	 * @date:2016年4月9日
	 * @param params
	 * @param coid
	 *            void
	 */
	void updateByField(Map<String, Object> params, String coid);

	/**
	 * @author:sunlingao@58.com
	 * @desc: 更新简历联系方式
	 * @date:2016年4月9日
	 * @param mobile
	 * @param email
	 * @param name
	 * @param coid
	 *            void
	 */
	void updateForContract(String mobile, String email, String name, String coid);

	/**
	 * 获取用户未删除简历
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月29日
	 * @param uid
	 * @return int
	 */
	int getCvCountForUid(String uid);

	/**
	 * 获取用户所有未删除简历id
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月29日
	 * @param uid
	 * @return List<Integer>
	 */
	List<String> getCvidsForUid(String uid);
	
	/**
     * 查询根据时间戳获取简历总量
     * @author:sunlingao@58.com
     * @date:2016年5月5日
     * @param key
     * @param date
     * @return
     * int
     */
    int getCvCountByTime(String key,long date);
    
    /**
     * 查询根据时间戳获取简历总量(分页查询)
     * @author:sunlingao@58.com
     * @date:2016年5月5日
     * @param key 排序值
     * @param date 时间戳
     * @param sort 排序
     * @param skip 分页起始
     * @param limit 分页大小
     * @return
     * List<CvInfoBo>
     */
    List<CvInfoBo> getCvCountByTimePageList(String key, long date, Direction sort, int skip,
            int limit);

}
