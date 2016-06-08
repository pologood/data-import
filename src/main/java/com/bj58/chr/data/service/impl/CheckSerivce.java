package com.bj58.chr.data.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bj58.chr.data.handler.NewHandler;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.service.ICheckSerivce;
import com.bj58.chr.data.service.ICvIdCoIdService;
import com.bj58.chr.data.utils.QDStringUtils;
import com.google.common.collect.Maps;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月12日 下午6:08:02
 * @see
 * @since
 */
@Service
public class CheckSerivce implements ICheckSerivce {

	private final static Logger LOG = LoggerFactory.getLogger(CheckSerivce.class);

	@Resource
	private ICvIdCoIdService cvIdCoIdService;

	@Resource
	private NewHandler newHandler;

	private final long time = getTime();

	@SuppressWarnings("rawtypes")
	@Override
	public void checkNew(String path) {
		File file = new File(path);
		File bak = new File(path + ".bak");
		File bak2 = new File(path + ".bak2");
		LOG.info(file.getAbsolutePath());
		if (file.exists()) {
			BufferedReader fos = null;
			try {
				fos = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = fos.readLine()) != null) {
					try {
						line = line.trim();
						CvIdCoId cvIdCoId = cvIdCoIdService.findById(new CvIdCoId(line));
						LOG.info(cvIdCoId.toString());
						if (cvIdCoId != null) {
							String cvId = cvIdCoId.getCvId();
							CvInfoBo cvInfoBo = newHandler.findOne(cvId);
							if (cvInfoBo != null) {
								LOG.info(cvInfoBo.getId());
								Map map = newHandler.getReg(cvInfoBo.getUid());
								long c = NumberUtils.toLong(String.valueOf(map.get("regTime")));
								if (c < time) {
									FileUtils.writeLines(bak, Arrays.asList(line), true);
								}
							} else {
								FileUtils.writeLines(bak2, Arrays.asList(line), true);
							}
						}
					} catch (Exception e) {
						LOG.error("error", e);
					}
				}
			} catch (Exception e) {
				LOG.error("", e);
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Object, Object> checkCoId(String coId) {
		CvIdCoId cvIdCoId = cvIdCoIdService.findById(new CvIdCoId(coId));
		if (cvIdCoId != null) {
			String cvId = cvIdCoId.getCvId();
			CvInfoBo cvInfoBo = newHandler.findOne(cvId);
			if (cvInfoBo != null) {
				LOG.info(cvInfoBo.getId());
				HashMap<Object, Object> map = Maps.newHashMap();
				map.putAll(newHandler.getReg(cvInfoBo.getUid()));
				return map;
			}
		}
		return Maps.newHashMap();
	}

	private long getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 3, 27, 0, 0, 0);
		System.out.println(DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss"));
		return QDStringUtils.getCvTime(calendar.getTimeInMillis());
	}

}
