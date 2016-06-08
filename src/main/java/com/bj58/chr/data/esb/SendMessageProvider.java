package com.bj58.chr.data.esb;


import java.io.IOException;

import com.bj58.chr.common.JSONUtils;
import com.bj58.spat.esbclient.ESBClient;
import com.bj58.spat.esbclient.ESBMessage;

public class SendMessageProvider<T> {
	
	private int subject;
	private final ESBClient esbClient;
	private static final int SENDWORKERCOUNT = 2;
	
	
	public SendMessageProvider(String url, int subject) throws IOException {
		super();
		this.subject = subject;
		esbClient = new ESBClient(url,SENDWORKERCOUNT);
	}	

	public void send(T messge) throws Exception {
		String jsonStr = JSONUtils.writeValue(messge);
		esbClient.send(new ESBMessage(subject, jsonStr.getBytes()));
	}

}
