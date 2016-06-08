package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.ICvIdCoIdDao;
import com.bj58.chr.data.dao.mapper.CvIdCoIdMapper;
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
public class CvIdCoIdDao extends SuperDao<CvIdCoId> implements ICvIdCoIdDao {

	@Resource
	private CvIdCoIdMapper cvIdCoIdMapper;

	@Override
	public SuperMapper<CvIdCoId> getMapper() {
		return cvIdCoIdMapper;
	}

}
