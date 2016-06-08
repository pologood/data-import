package com.bj58.chr.data.model;

import com.bj58.chr.data.model.kv.KVGroup;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月14日 下午6:33:39
 * @see
 * @since
 */
public class XlsUpload {

	private int sheet;
	private String xls;
	private KVGroup group;

	public int getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = sheet;
	}

	public String getXls() {
		return xls;
	}

	public void setXls(String xls) {
		this.xls = xls;
	}

	public KVGroup getGroup() {
		return group;
	}

	public void setGroup(KVGroup group) {
		this.group = group;
	}

}
