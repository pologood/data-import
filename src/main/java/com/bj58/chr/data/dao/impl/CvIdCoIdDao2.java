package com.bj58.chr.data.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.ICvIdCoIdDao2;
import com.bj58.chr.data.dao.mapper.CvIdCoIdMapper2;
import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:16:56
 * @see
 * @since
 */
@Repository
public class CvIdCoIdDao2 extends SuperDao<CvIdCoId> implements ICvIdCoIdDao2 {

	@Resource
	private CvIdCoIdMapper2 cvIdCoIdMapper2;

	@Override
	public SuperMapper<CvIdCoId> getMapper() {
		return cvIdCoIdMapper2;
	}

	@Override
	public List<CvIdCoId> getByCvId(Map<String, Object> map) {
		return cvIdCoIdMapper2.getByCvId(map);
	}

}
