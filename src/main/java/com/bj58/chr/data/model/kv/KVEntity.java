package com.bj58.chr.data.model.kv;

import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午9:17:24
 * @see
 * @since
 */
@Alias("KVEntity")
public class KVEntity extends IntPersistable {
	private static final long serialVersionUID = -6585619692184660922L;
	private KVGroup kVGroup;
	private String qdId;
	private String qdName;
	private String ycId;
	private String ycName;
	private int root;

	public KVGroup getkVGroup() {
		return kVGroup;
	}

	public void setkVGroup(KVGroup kVGroup) {
		this.kVGroup = kVGroup;
	}

	public String getQdName() {
		return qdName;
	}

	public void setQdName(String qdName) {
		this.qdName = qdName;
	}

	public String getYcName() {
		return ycName;
	}

	public void setYcName(String ycName) {
		this.ycName = ycName;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public String getQdId() {
		return qdId;
	}

	public void setQdId(String qdId) {
		this.qdId = qdId;
	}

	public String getYcId() {
		return ycId;
	}

	public void setYcId(String ycId) {
		this.ycId = ycId;
	}

}
