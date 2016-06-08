package com.bj58.chr.data.model;

import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午1:13:38
 * @see
 * @since
 */
@Alias("CvIdCoId")
public class CvIdCoId extends IntPersistable {

	private static final long serialVersionUID = 7869133264645554627L;
	private String coId;
	private String cvId;
	private String uid;
	private boolean yc;

	/**
	 * @param coid2
	 */

	public CvIdCoId() {
	}

	public CvIdCoId(String coId) {
		this.coId = coId;
	}

	public String getCoId() {
		return coId;
	}

	public void setCoId(String coId) {
		this.coId = coId;
	}

	public String getCvId() {
		return cvId;
	}

	public void setCvId(String cvId) {
		this.cvId = cvId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean isYc() {
		return yc;
	}

	public void setYc(boolean yc) {
		this.yc = yc;
	}

	@Override
	public String toString() {
		return "CvIdCoId [coId=" + coId + ", cvId=" + cvId + ", uid=" + uid + ", yc=" + yc + "]";
	}

}
