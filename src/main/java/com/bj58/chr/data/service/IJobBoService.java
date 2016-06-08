package com.bj58.chr.data.service;

import java.util.Map;

import com.bj58.chr.api.scf.entity.company.JobMappingBo;
import com.bj58.chr.api.scf.entity.company.JobsBo;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date  2016年5月19日 下午9:54:56
 * @see 
 * @since  
 */
public interface IJobBoService {

	/**
	 * @param jobId
	 * @return
	 */
	JobsBo findById(String jobId);

	/**
	 * @param jobId
	 * @return
	 */
	Map<String, Map<String, String>> findJobParams(String jobId);

	/**
	 * @param bo
	 */
	void insertJobMapping(JobMappingBo bo);

}
