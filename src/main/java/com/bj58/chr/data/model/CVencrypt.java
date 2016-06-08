package com.bj58.chr.data.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:09:03
 * @see
 * @since
 */
@Alias("CVencrypt")
public class CVencrypt extends IntPersistable {

	private static final long serialVersionUID = 2199429703101614536L;

	private String cvid;
	private String coid;
	private String encrpt;
	private String uid;
	private Date uptime;
	private String original;

	public String getCvid() {
		return cvid == null ? StringUtils.EMPTY : cvid;
	}

	public void setCvid(String cvid) {
		this.cvid = cvid;
	}

	public String getCoid() {
		return coid == null ? StringUtils.EMPTY : cvid;
	}

	public void setCoid(String coid) {
		this.coid = coid;
	}

	public String getEncrpt() {
		return encrpt == null ? StringUtils.EMPTY : encrpt;
	}

	public void setEncrpt(String encrpt) {
		this.encrpt = encrpt;
	}

	public Date getUptime() {
		return uptime == null ? new Date() : uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOriginal() {
		return original == null ? StringUtils.EMPTY : original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	@Override
	public String toString() {
		return "CVencrypt [cvid=" + cvid + ", coid=" + coid + ", encrpt=" + encrpt + ", uid=" + uid + ", uptime="
				+ uptime + ", original=" + original + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cvid == null) ? 0 : cvid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CVencrypt other = (CVencrypt) obj;
		if (cvid == null) {
			if (other.cvid != null)
				return false;
		} else if (!cvid.equals(other.cvid))
			return false;
		return true;
	}

}
