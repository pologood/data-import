package com.bj58.chr.data.model.wb;

public class RegisterReturnVo {
	
	private String uid;
	
	private String gateway_success;
	
	private String msg;
	
	private String expires_in;
	
	private String refresh_token;
	
	private String access_token;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getGateway_success() {
		return gateway_success;
	}

	public void setGateway_success(String gateway_success) {
		this.gateway_success = gateway_success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
}
