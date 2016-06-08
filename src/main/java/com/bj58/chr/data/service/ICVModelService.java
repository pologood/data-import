package com.bj58.chr.data.service;

import com.bj58.chr.data.model.qdcv.CVModel;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午5:56:34
 * @see
 * @since
 */
public interface ICVModelService {

	void saveOrUpdate(CVModel cvModel);

	void save(CVModel cvModel);

	void update(CVModel cvModel);

	CVModel findByCoid(String coid);

}
