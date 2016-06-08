package com.bj58.chr.data.esb;

import org.apache.log4j.Logger;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.data.esb.message.Message;

/**
 * 简历esb消息通道
 * 
 * @author sunlingao@58.com
 * @date 2016年4月8日
 */
public class MessageSend {

	private final Logger logger = Logger.getLogger(MessageSend.class);
	private SendMessageProvider<Message> provider;

	private String esbUrl;

	public String getEsbUrl() {
		return esbUrl;
	}

	public void setEsbUrl(String esbUrl) {
		this.esbUrl = esbUrl;
	}

	public int getEsbItem() {
		return esbItem;
	}

	public void setEsbItem(int esbItem) {
		this.esbItem = esbItem;
	}

	private int esbItem;

	private MessageSend(String esbUrl, int esbItem) {
		try {
			this.esbItem = esbItem;
			this.esbUrl = esbUrl;
			provider = new SendMessageProvider<Message>(this.esbUrl, this.esbItem);
		} catch (Exception e) {
			logger.error("初始化消息发送Provider", e);
		}
	}

	public void send(Message msg) {
		if (logger.isDebugEnabled()) {
			logger.debug(JSONUtils.writeValue(msg));
		}
		try {
			provider.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送简历变更消息出现异常", e);
		}

	}

}
