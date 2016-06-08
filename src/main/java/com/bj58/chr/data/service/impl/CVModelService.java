package com.bj58.chr.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.service.ICVModelService;
import com.bj58.chr.data.service.IPipelineService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午5:57:16
 * @see
 * @since
 */
@Service
public class CVModelService implements ICVModelService {

	@Resource
	private IPipelineService pipelineService;

	@Override
	public void saveOrUpdate(CVModel cvModel) {
		String coid = cvModel.getCoid();
		// 判断这个合作ID在库里是不是存在
		CVModel dbcvModel = findByCoid(coid);
		// 保存或者更新到数据库
		if (dbcvModel == null) {// 不存在这个简历
			save(cvModel);
		} else {
			update(cvModel);
		}
		// 往搜索流水表中添加一条记录
		pipelineService.add(cvModel);
	}

	// 保存简历到数据库
	@Override
	public void save(CVModel cvModel) {
	}

	// 更新简历到数据库
	@Override
	public void update(CVModel cvModel) {

	}

	// 通过合作id或者简历
	@Override
	public CVModel findByCoid(String coid) {
		return null;
	}

}
