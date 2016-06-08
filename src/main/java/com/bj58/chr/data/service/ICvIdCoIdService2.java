package com.bj58.chr.data.service;

import java.util.List;
import java.util.Map;

import com.bj58.chr.common.web.mybatis.ISuperService;
import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:18:39
 * @see
 * @since
 */
public interface ICvIdCoIdService2 extends ISuperService<CvIdCoId> {

	/**
	 * @param map
	 * @return
	 */
	List<CvIdCoId> getByCvId(Map<String, Object> map);

}
