package com.bj58.chr.data.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.chr.data.model.CVAsk;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.service.ICVencryptService;
import com.bj58.chr.data.service.IFxService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月13日 下午3:11:23
 * @see
 * @since
 */
public class FxService extends FileReadService<CVAsk> implements IFxService {

	// 主要是分析总共有多少匹配上了，但是我们通过了，计算损失比例

	private final static Logger LOG = LoggerFactory.getLogger(UpdateData.class);

	private ICVencryptService cVencryptService;

	@Override
	public void handlerFile(CVAsk cvAsk) {
		String e = cvAsk.getEdigest();
		List<CVencrypt> cVencrypt = cVencryptService.findByEncrypt(e);
		if (cVencrypt != null) {

		} else {
			LOG.error("not find encrypt", e);
		}
	}

}
