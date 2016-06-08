package com.bj58.chr.data.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @author sunlingao@58.com
 * @date 2016年4月9日
 */
public class KeyUtils {

	private final static AtomicLong index = new AtomicLong();

	public final static String radix36(long value) {
		return Long.toString(value, 36);
	}

	public final static String getRandom() {
		return radix36(System.currentTimeMillis() / 1000 + index.incrementAndGet());
	}

	public static void main(String[] args) {
		System.out.println(getRandom());
	}

}
