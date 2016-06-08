package com.bj58.chr.data.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.data.dao.ICvIdCoIdDao2;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.ICvIdCoIdService2;

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
public class CvIdCoIdService2 extends ShardSuperService<CvIdCoId> implements ICvIdCoIdService2 {

	@Resource
	private ICvIdCoIdDao2 cvIdCoIdDao2;

	@Override
	public ISuperDao<CvIdCoId> getSuperDao() {
		return cvIdCoIdDao2;
	}

	@Override
	public List<CvIdCoId> getByCvId(Map<String, Object> map) {
		return cvIdCoIdDao2.getByCvId(map);
	}

}
