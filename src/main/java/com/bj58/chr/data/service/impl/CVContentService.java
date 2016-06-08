package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.ICVContentDao;
import com.bj58.chr.data.dao.impl.CVContentDao;
import com.bj58.chr.data.model.CVContent;
import com.bj58.chr.data.service.ICVContentService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月8日 上午10:57:48
 * @see
 * @since
 */
@Service
public class CVContentService extends SuperService<CVContent> implements ICVContentService {

	@Resource
	private ICVContentDao cVContentDao;

	@Override
	public ISuperDao<CVContent> getSuperDao() {
		return cVContentDao;
	}

}
