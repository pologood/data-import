package com.bj58.chr.data.execption;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:20:55
 * @see
 * @since
 */
public class UnSupportMethodException extends RuntimeException {

	private static final long serialVersionUID = 6180156803222408317L;

	@Override
	public String getMessage() {
		return "不支持这个方法";
	}

}
