package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.ICVContentDao;
import com.bj58.chr.data.dao.mapper.CVContentMapper;
import com.bj58.chr.data.model.CVContent;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月8日 上午10:54:29
 * @see
 * @since
 */
@Repository
public class CVContentDao extends SuperDao<CVContent> implements ICVContentDao {

	@Resource
	private CVContentMapper cVContentMapper;

	@Override
	public SuperMapper<CVContent> getMapper() {
		return cVContentMapper;
	}

}
