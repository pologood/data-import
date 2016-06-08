package com.bj58.chr.data.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bj58.chr.common.web.mybatis.ISuperDao;
import com.bj58.chr.common.web.mybatis.SuperService;
import com.bj58.chr.data.dao.ICVencryptDao;
import com.bj58.chr.data.model.CVAsk;
import com.bj58.chr.data.model.CVencrypt;
import com.bj58.chr.data.model.CVencryptCheck;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.SeekerUser;
import com.bj58.chr.data.service.ICVencryptService;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.service.ISeekerUserService;
import com.bj58.chr.data.utils.NamedThreadFactory;
import com.bj58.chr.data.utils.QDencrypt;
import com.google.common.collect.Sets;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:11:55
 * @see
 * @since
 */
@Service
public class CVencryptService extends SuperService<CVencrypt> implements ICVencryptService {

	private final static Logger LOG = LoggerFactory.getLogger(CVencryptService.class);

	private final static ExecutorService executorService = Executors.newFixedThreadPool(10,
			new NamedThreadFactory("cvencrypt_"));

	@Resource
	private ICVencryptDao cVencryptDao;

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Resource
	private ISeekerUserService seekerUserService;

	@Override
	public ISuperDao<CVencrypt> getSuperDao() {
		return cVencryptDao;
	}

	@Override
	public CVencryptCheck getCVencrypt(CVAsk cvask, CvIdCoId cvIdCoId) {
		List<CVencrypt> cvId2 = null;
		Set<CVencrypt> cvMore = null;
		if (StringUtils.isNotEmpty(cvask.getMndigest())) {
			cvId2 = cVencryptDao.findByEncrypt(cvask.getMndigest());
			cvMore = Sets.newHashSet();
			cvMore.addAll(cvId2);
			if (CollectionUtils.isNotEmpty(cvMore)) {
				try {
					if (cvIdCoId == null) {
						String uid = cvMore.iterator().next().getUid();
						seekerUserService.save(new SeekerUser(uid, "", cvask.getCoid()));
					}
				} catch (Exception e) {
					LOG.error("save seeker error", e);
				}
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(cvId2 + "," + cvask + "," + cvIdCoId);
		}
		return new CVencryptCheck(cvMore, cvask, cvIdCoId);
	}

	@Override
	public void add(final CVencrypt cVencrypt) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					if (cVencrypt != null) {
						cVencryptDao.save(cVencrypt);
					} else {
						LOG.info("get form queue null");
					}
				} catch (Exception e) {
					LOG.error("error", e);
				}
			}
		});
	}

	@Override
	public List<CVencrypt> findByEncrypt(String encrypt) {
		return cVencryptDao.findByEncrypt(encrypt);
	}

}
