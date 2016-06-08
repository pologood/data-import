package com.bj58.chr.data.model.kv;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午10:40:36
 * @see
 * @since
 */
public class KVName {

	private String id;
	private String name;

	/**
	 * @param ycId
	 * @param ycName
	 */
	public KVName(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
