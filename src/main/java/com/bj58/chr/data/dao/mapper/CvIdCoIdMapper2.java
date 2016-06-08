package com.bj58.chr.data.dao.mapper;

import java.util.List;
import java.util.Map;

import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:15:48
 * @see
 * @since
 */
public interface CvIdCoIdMapper2 extends SuperMapper<CvIdCoId> {

	public List<CvIdCoId> getByCvId(Map<String, Object> params);

}
