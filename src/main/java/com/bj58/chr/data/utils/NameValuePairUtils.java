package com.bj58.chr.data.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月20日 上午12:31:16
 * @see
 * @since
 */
public class NameValuePairUtils {

	private List<NameValuePair> params = new ArrayList<>();

	private NameValuePairUtils() {

	}

	public NameValuePairUtils add(String key, String value) {
		params.add(new BasicNameValuePair(key, value));
		return this;
	}

	public List<NameValuePair> get() {
		return params;
	}

	public final static NameValuePairUtils create() {
		return new NameValuePairUtils();
	}

	/**
	 * @param value
	 */
	public NameValuePairUtils add(Map<String, String> value) {
		for (Entry<String, String> entry : value.entrySet()) {
			add(entry.getKey(), entry.getValue());
		}
		return this;
	}

}
