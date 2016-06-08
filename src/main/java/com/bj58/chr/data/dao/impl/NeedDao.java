package com.bj58.chr.data.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.data.dao.INeedDao;
import com.bj58.chr.data.dao.mapper.NeedMapper;
import com.bj58.chr.data.model.Need;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午7:04:53
 * @see
 * @since
 */
@Repository
public class NeedDao extends SuperDao<Need> implements INeedDao {

	@Resource
	private NeedMapper needMapper;

	@Override
	public SuperMapper<Need> getMapper() {
		return needMapper;
	}

	@Override
	public void fulfil(String coid) {
		needMapper.fulfil(coid);
	}

}
