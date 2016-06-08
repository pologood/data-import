package com.bj58.chr.data.model;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.bj58.chr.common.JSONUtils;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月30日 下午11:46:34
 * @see
 * @since
 */
@Alias("CVAsk")
public class CVAsk extends CoId {

	private static final long serialVersionUID = 5624601055077412259L;

	@NotBlank(message = "不能为空")
	@Length(min = 32, max = 128, message = "用户名+手机加密串错误")
	private String mndigest;
	private String edigest;
	@NotBlank(message = "不能为空")
	@Length(min = 10, max = 10, message = "更新时间错误")
	private String uptime;

	public String getMndigest() {
		return mndigest;
	}

	public void setMndigest(String mndigest) {
		this.mndigest = mndigest;
	}

	public String getEdigest() {
		return edigest;
	}

	public void setEdigest(String edigest) {
		this.edigest = edigest;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	@Override
	public String toString() {
		return JSONUtils.writeValue(this);
	}

}
