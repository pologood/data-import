package com.bj58.chr.data.model;

import com.bj58.chr.common.web.SuperEnum;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 上午10:30:48
 * @see
 * @since
 */
public enum CVencryptCheckEnum implements SuperEnum<CVencryptCheckEnum> {

	FullMatch("FullMatch"), MobileMatch("MobileMatch"), EmailMatch("EmailMatch"), NotMatch("NotMatch");

	private String name;

	/**
	 * @param name
	 */
	private CVencryptCheckEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public CVencryptCheckEnum getValue(int index) {
		return values()[index];
	}

	@Override
	public int getIndex() {
		return this.ordinal();
	}

}
