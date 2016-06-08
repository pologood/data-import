package com.bj58.chr.data.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.chr.common.web.page.ParamUtils;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.service.ICvIdCoIdService2;
import com.bj58.chr.data.utils.NamedThreadFactory;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月16日 下午8:24:21
 * @see
 * @since
 */
public abstract class CvIdCoIdLoopService {

	private final static Logger LOG = LoggerFactory.getLogger(CvIdCoIdLoopService.class);

	@Resource
	private ICvIdCoIdService2 cvIdCoIdService2;

	private final static ExecutorService executorService = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(), new NamedThreadFactory("cvid-coid-thread"), new DiscardPolicy());

	public void runTask() {
		LOG.info("start.......");
		int id;
		for (int i = 0; i < 30; i++) {
			String table = "cv_idcoid_" + i;
			id = 0;
			boolean run = true;
			LOG.info("start:" + table);
			while (run) {
				try {
					List<CvIdCoId> list = cvIdCoIdService2
							.list(ParamUtils.createParam().add("table", table).add("id", id).get());
					if (CollectionUtils.isEmpty(list)) {
						run = false;
					} else {
						for (final CvIdCoId cvIdCoId : list) {
							if (cvIdCoId != null) {
								executorService.submit(new Runnable() {
									@Override
									public void run() {
										runTask(cvIdCoId);
									}
								});
							}
							if (cvIdCoId.getId() > id) {
								id = cvIdCoId.getId();
							}
						}
					}
					id++;
				} catch (Exception e) {
					LOG.error("get cvId coId", e);
				}
			}
		}
	}

	public abstract void runTask(final CvIdCoId cvIdCoId);

}
