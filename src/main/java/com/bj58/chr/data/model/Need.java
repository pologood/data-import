package com.bj58.chr.data.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午5:47:27
 * @see
 * @since
 */
@Alias("Need")
public class Need extends IntPersistable {

	private static final long serialVersionUID = -8117658701106215262L;

	private String coid;

	private Date createdTime;

	private boolean need;

	/**
	 * @param coid2
	 */
	public Need(String coid) {
		this.coid = coid;
		this.createdTime = new Date();
	}

	public String getCoid() {
		return coid;
	}

	public void setCoid(String coid) {
		this.coid = coid;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public boolean isNeed() {
		return need;
	}

	public void setNeed(boolean need) {
		this.need = need;
	}

}
