package com.bj58.chr.data.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.apache.log4j.Logger;

import com.bj58.chr.common.JSONUtils;
import com.bj58.chr.common.web.IntPersistable;

/**
 * 
 * 
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午4:10:19
 * @see
 * @since
 */
@Alias("CVContract")
public class CVContract extends IntPersistable {

	private static Logger logger = Logger.getLogger(CVContract.class);
	private static final long serialVersionUID = 1L;
	/**1:qdimport 2:pc 3:58import*/
	private int status;
	private Data data;
	private String coid;// 请求的合作Id
	private String uid;
	private Date createdTime;// 创建时间
	private Date updateTime;// 更新时间
	private Date loginTime;// 登录时间

	public String getCoid() {
		return coid;
	}

	public void setCoid(String coid) {
		this.coid = coid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return JSONUtils.writeValue(this);
	}

	public static class Data {
		private String emails;
		private String namemobiles;

		public String getEmails() {
			return emails;
		}

		public void setEmails(String emails) {
			this.emails = emails;
		}

		public String getNamemobiles() {
			return namemobiles;
		}

		public void setNamemobiles(String namemobiles) {
			this.namemobiles = namemobiles;
		}

		public String getNewEmail() {
			return getLastSplitStr(emails, ";");
		}

		public String getNewNameMobile() {
			return getLastSplitStr(namemobiles, ";");
		}

		public String getNewName() {
			String str = getNewNameMobile();
			if (!StringUtils.isEmpty(str)) {
				return str.split(",")[0];
			}
			return "";
		}

		public String getNewMobile() {
			String str = getNewNameMobile();
			try {
				if (!StringUtils.isEmpty(str)) {
					return str.split(",")[1];
				}
			} catch (Exception e) {
				logger.error(String.format("error msg is %s", str), e);
				return "";
			}
			return "";
		}

		private String getLastSplitStr(String str, String splitStr) {
			if (!StringUtils.isEmpty(str)) {
				String arr[] = str.split(splitStr);
				return arr[arr.length - 1];
			}
			return "";
		}

		@Override
		public String toString() {
			return "Data [emails=" + emails + ", namemobiles=" + namemobiles + "]";
		}

	}

}
