package com.bj58.chr.data.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bj58.chr.common.web.mybatis.SuperDao;
import com.bj58.chr.common.web.mybatis.SuperMapper;
import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.dao.ICVencryptDao;
import com.bj58.chr.data.dao.mapper.CVencryptMapper;
import com.bj58.chr.data.model.CVencrypt;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:13:40
 * @see
 * @since
 */
@Repository
public class CVencryptDao extends SuperDao<CVencrypt> implements ICVencryptDao {

	@Resource
	private CVencryptMapper cVencryptMapper;

	@Override
	public SuperMapper<CVencrypt> getMapper() {
		return cVencryptMapper;
	}

	@Override
	public List<CVencrypt> findByEncrypt(String encrypt) {
		return cVencryptMapper.findByEncrypt(ParamUtils.createParam().add("encrpt", encrypt).get());
	}

}
