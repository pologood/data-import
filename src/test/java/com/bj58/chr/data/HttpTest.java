package com.bj58.chr.data;

import java.util.List;
import java.util.UUID;

import org.apache.commons.validator.Var;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.bj58.chr.common.HttpUtils;
import com.google.common.collect.Lists;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月5日 下午5:09:16
 * @seecd
 * @since
 */
public class HttpTest {
	@Test
	public void test() {
		List<NameValuePair> params = Lists.newArrayList();
		params.add(new BasicNameValuePair("coid", "87e1b0d3-ee9f-4ae3-b798-87a96e695ec4"));
		params.add(new BasicNameValuePair("edigest", "edbe5ae92e06f3ab78e5e194f3d9f42d"));
		params.add(new BasicNameValuePair("mndigest", "f5ccd10dc95a273e7d57590b3f883961"));
		params.add(new BasicNameValuePair("uptime", "2016.05.16"));
		String result = HttpUtils.doGet("http://10.0.4.33:8069/cv/ask", params);
		System.out.println(result);
	}

}
