package com.bj58.chr.data.model.kv;

import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:16:09
 * @see
 * @since
 */
@Alias("KVGroup")
public class KVGroup extends IntPersistable {

	private static final long serialVersionUID = -6663474005394687152L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
