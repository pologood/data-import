package com.bj58.chr.data.service;

import com.bj58.chr.data.model.CvIdCoId;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月16日 下午6:49:04
 * @see
 * @since
 */
public interface INoContractService {

	public void runTask(CvIdCoId cvIdCoId);

	void setFile(String file);
}
