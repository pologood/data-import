package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.dao.ICvIdCoIdDao;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.ICvIdCoIdService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:19:07
 * @see
 * @since
 */
@Service
public class CvIdCoIdService extends ShardSuperService<CvIdCoId> implements ICvIdCoIdService {

	@Resource
	private ICvIdCoIdDao cvIdCoIdDao;

	@Override
	public ISuperDao<CvIdCoId> getSuperDao() {
		return cvIdCoIdDao;
	}

}
