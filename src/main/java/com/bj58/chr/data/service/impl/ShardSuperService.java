package com.bj58.chr.data.service.impl;

import java.io.Serializable;

import com.bj58.chr.common.web.IPersistable;
import com.bj58.chr.common.web.mybatis.SuperService;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月4日 下午9:53:08
 * @see
 * @since
 */
public abstract class ShardSuperService<T extends IPersistable<? extends Serializable>> extends SuperService<T> {

	@Override
	public void remove(T t) {
		this.getSuperDao().remove(t);
	}

}
