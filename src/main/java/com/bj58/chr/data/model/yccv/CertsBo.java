package com.bj58.chr.data.model.yccv;

/**
 * 证书实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class CertsBo {

	private String id;
	
	private String certName;
	
	private String school;
	
	private Long time;
	
	private String score;
	
	private String isDel;
	
	private long delTime;
	
	private long addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Long getDelTime() {
		return delTime;
	}

	public void setDelTime(long delTime) {
		this.delTime = delTime;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}
}
