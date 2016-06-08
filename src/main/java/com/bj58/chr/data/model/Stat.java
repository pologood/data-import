package com.bj58.chr.data.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月17日 下午6:50:15
 * @see
 * @since
 */
@Document(collection = "stat")
public class Stat {

	private String id;

	private Map<String, Object> map;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
