package com.bj58.chr.data.utils;

import com.bj58.chr.data.LogFactory;
import com.bj58.chr.data.log.Log;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午12:22:53
 * @see
 * @since
 */
public class LogUtils {

	private final static Log ASK = LogFactory.getLogger("ask");
	private final static Log CONTRACT = LogFactory.getLogger("contract");
	private final static Log CONTENT = LogFactory.getLogger("content");
	private final static Log ERROR = LogFactory.getLogger("error");

	public final static Log getLogger(String key) {
		return LogFactory.getLogger(key);
	}

	public final static void ask(String content) {
		ASK.add(content);
	}

	public final static void contract(String content) {
		CONTRACT.add(content);
	}

	public final static void content(String content) {
		CONTENT.add(content);
	}

	public final static void error(String content) {
		ERROR.add(content);
	}

}
