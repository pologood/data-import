package com.bj58.chr.data.dao;

import java.util.List;
import java.util.Map;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:16:23
 * @see
 * @since
 */
public interface ICvIdCoIdDao2 extends ISuperDao<CvIdCoId> {

	/**
	 * @param map
	 * @return
	 */
	List<CvIdCoId> getByCvId(Map<String, Object> map);

}
