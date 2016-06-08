package com.bj58.chr.data.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bj58.chr.data.model.CVAsk;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.IAsyncTaskService;
import com.bj58.chr.data.service.ICVencryptService;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.service.IUptimeData;
import com.bj58.chr.data.utils.QDencrypt;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月10日 下午11:39:39
 * @see
 * @since
 */
@Service
public class UptimeData extends FileReadService<CVAsk> implements IUptimeData {

	private final static Logger LOG = LoggerFactory.getLogger(UpdateData.class);

	@Resource
	private IAsyncTaskService asyncTaskService;

	@Resource
	private ICVencryptService cVencryptService;

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Override
	public void handlerFile(CVAsk cvAsk) {
		if (cvAsk != null) {
			LOG.info(cvAsk.toString());
			String uptime = cvAsk.getUptime();
			Date date = QDencrypt.getTime(uptime);
			List<CVencrypt> m = cVencryptService.findByEncrypt(cvAsk.getMndigest());
			if (CollectionUtils.isNotEmpty(m)) {
				// asyncTaskService.updateTimeByCvid(m.getCvid(), date);
				// LOG.info(m.getCvid() + "," + date);
			} else {
				CvIdCoId cvIdCoId = cvIdCoIdService.findById(new CvIdCoId(cvAsk.getCoid()));
				if (cvIdCoId != null && StringUtils.isNoneEmpty(cvIdCoId.getCvId())) {
					LOG.info(cvIdCoId.getCvId() + "," + date);
					asyncTaskService.updateTimeByCvid(cvIdCoId.getCvId(), cvIdCoId.getUid(), cvAsk.getCoid(), date);
				}
			}
		}
	}

}
