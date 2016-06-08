package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:58:04
 * @see
 * @since
 */
public class ResumeTag {

	private String srid;// 源网站id
	@JsonProperty("name_mobile")
	private String nameMobile;// 姓名+手机号
	private String email;// email
	@JsonProperty("class_b")
	private String classB;// B 无工作经历，n个教育经历：同时搜索匹配这两段的
	@JsonProperty("class_c")
	private String classC;// C 1工作经历，1教育经历：按（基本信息+教育经历）+ 公司名查询，匹配结果
	@JsonProperty("class_d")
	private String classD;// D 1教育经历，n工作经历：按（基本信息+教育经历）+ n个公司名 查询，匹配结果

	public String getSrid() {
		return srid;
	}

	public void setSrid(String srid) {
		this.srid = srid;
	}

	public String getNameMobile() {
		return nameMobile;
	}

	public void setNameMobile(String nameMobile) {
		this.nameMobile = nameMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassB() {
		return classB;
	}

	public void setClassB(String classB) {
		this.classB = classB;
	}

	public String getClassC() {
		return classC;
	}

	public void setClassC(String classC) {
		this.classC = classC;
	}

	public String getClassD() {
		return classD;
	}

	public void setClassD(String classD) {
		this.classD = classD;
	}

}
