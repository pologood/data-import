package com.bj58.chr.data.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bj58.chr.api.scf.entity.company.JobMappingBo;
import com.bj58.chr.api.scf.entity.company.JobsBo;
import com.bj58.chr.data.service.IJobBoService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月19日 下午9:55:06
 * @see
 * @since
 */
@Service
public class JobBoService implements IJobBoService {

	@Override
	public JobsBo findById(String jobId) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bj58.chr.data.service.IJobBoService#findJobParams(java.lang.String)
	 */
	@Override
	public Map<String, Map<String, String>> findJobParams(String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bj58.chr.data.service.IJobBoService#insertJobMapping(com.bj58.chr.api
	 * .scf.entity.company.JobMappingBo)
	 */
	@Override
	public void insertJobMapping(JobMappingBo bo) {
		// TODO Auto-generated method stub

	}

}
