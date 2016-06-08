package com.bj58.chr.data.esb.message;

import java.util.Date;

/**
 * 消息实体
 * @author sunlingao@58.com
 * @date 2016年4月8日
 */
public class CvPostMessage extends Message{

	/**
     * date:2016年4月8日
     * field:serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String uid;
	
	private int type;
	
	private String method;
	
	private String requestInfo;
	
	private Date postTime;
	
	public CvPostMessage(String id,String uid,String content,String method,int type,String requestInfo){
	    setId(id);
	    this.uid = uid;
	    this.content = content;
	    this.method = method;
	    this.type = type;
	    this.requestInfo = requestInfo;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
}
