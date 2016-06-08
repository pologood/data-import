package com.bj58.chr.data.model;

import java.util.Date;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.common.web.IntPersistable;
import com.bj58.chr.common.web.Status;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月7日 下午1:59:38
 * @see
 * @since
 */
public class Rule extends IntPersistable {

	private static final long serialVersionUID = 7751596025429309459L;

	private String name;

	private String template;

	private boolean test;

	private String abtest;

	private String note;

	private String result;

	private Date createTime;

	private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public String getAbtest() {
		return abtest;
	}

	public void setAbtest(String abtest) {
		this.abtest = abtest;
	}

	public Status<String> getResult() {
		return JSONUtils.readValue(result, new TypeReference<Status<String>>() {
		});
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
