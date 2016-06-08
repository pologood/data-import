package com.bj58.chr.data.utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:00:23
 * @see
 * @since
 */
public class QDencrypt {

	public final static String encrypt(String name, String mobile) {
		return encrypt(name + "," + mobile);
	}

	public final static String encrypt(String source) {
		for (int i = 0; i < 3; i++) {
			source = DigestUtils.sha1Hex(source);
		}
		return DigestUtils.md5Hex(source);
	}

	public final static Date getTime(String uptime) {
		Date date = null;
		try {
			date = DateUtils.parseDate(uptime, "yyyy.MM.dd");
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}

	public static void main(String[] args) {
		List<String> values = Arrays.asList("闵大双,13716064866", "mindashuang@58ganji.com", "测试手机,13800013800");
		for (String value : values) {
			System.out.println(value + "==" + encrypt(value));
		}
	}

}
