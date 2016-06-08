package com.bj58.chr.data.model;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月6日 下午2:39:26
 * @see
 * @since
 */
public class Config extends IntPersistable {

	private static final long serialVersionUID = -7017305402801922688L;
	private String name;
	private String value;
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
