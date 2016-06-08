package com.bj58.chr.data.handler;

import java.util.Map;

/**
 * 简历同步处理器
 * 
 * @author sunlingao@58.com
 * @date 2016年4月29日
 */
public interface SyncHandler<T> {

	/**
	 * 根据简历信息获取用户态(判断用户状态)
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月29日
	 * @param bo
	 * @return UserStatusEnum
	 */
	public UserStatusEnum getUserStatus(T t);

	/**
	 * 更新操作
	 * 
	 * @author:sunlingao@58.com
	 * @date:2016年4月29日
	 * @param t
	 *            void
	 */
	public T update(T t);

	/**
	 * 用户状态
	 * 
	 * @author sunlingao@58.com
	 * @date 2016年4月29日
	 */
	public enum UserStatusEnum {
		/** 未找到用户 */
		NULL(0),
		/** 用户已过期 */
		ALLXP(1), LOGINXP(2), NOTXP(3);

		private int code;

		private String uid;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		UserStatusEnum(int code) {
			this.code = code;
		}
	}

}
