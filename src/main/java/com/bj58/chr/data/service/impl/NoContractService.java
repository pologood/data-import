package com.bj58.chr.data.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bj58.chr.data.model.CVContract;
import com.bj58.chr.data.model.CvIdCoId;
import com.bj58.chr.data.model.CVContract.Data;
import com.bj58.chr.data.service.IContractService;
import com.bj58.chr.data.service.INoContractService;

/**
 * 查找有合作ID但是没有
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月16日 下午6:49:16
 * @see
 * @since
 */
@Service
public class NoContractService extends CvIdCoIdLoopService implements INoContractService {

	private final static Logger LOG = LoggerFactory.getLogger(NoContractService.class);

	// private final Pattern mobilePattern = Pattern.compile("");
	// (0|86|17951)?(1[0-9]{10}

	// private final Pattern nameMobilePattern = Pattern.compile("");
	// [^x00-xff]{1,},[(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}]{1,}

	@Resource
	private IContractService contractService;

	private File file;

	@Override
	public void setFile(String file) {
		this.file = new File(file);
	}

	@Override
	public void runTask(CvIdCoId cvIdCoId) {
		CVContract contract = contractService.findByCoId(cvIdCoId.getCoId());
		if (contract != null) {
			// 假如存在链接方式，判断是否为空
			Data data = contract.getData();
			if (data != null) {
				// String nameMobiles = data.getNamemobiles();
				// String newMobile = data.getNewMobile();
				// if (StringUtils.isEmpty(nameMobiles)) {
				// write(cvIdCoId.getCoId() + ",nameMobiles is null");
				// } else {// 判断是否是名字+电话
				// if (!isNameMobile(nameMobiles)) {// 判断是否是电话
				// write(cvIdCoId.getCoId() + ",nameMobiles is error");
				// }
				// }
				// if (StringUtils.isEmpty(newMobile)) {
				// write(cvIdCoId.getCoId() + ",newMobile is null");
				// } else {// 判断是否是电话
				// if (!isMobile(newMobile)) {// 判断是否是电话
				// write(cvIdCoId.getCoId() + ",newMobile is error");
				// }
				// }

			} else {
				write(cvIdCoId.getCoId() + ",data is null");
			}
		} else {
			write(cvIdCoId.getCoId());
		}
	}

	private void write(String line) {
		try {
			FileUtils.writeLines(file, Arrays.asList(line), true);
		} catch (IOException e) {
			LOG.error("file error", e);
		}
	}

	// private boolean isMobile(String mobile) {
	// Matcher matcher = mobilePattern.matcher(mobile);
	// return matcher.matches();
	// }
	//
	// private boolean isNameMobile(String nameMobiles) {
	// Matcher matcher = nameMobilePattern.matcher(nameMobiles);
	// return matcher.matches();
	// }

}
