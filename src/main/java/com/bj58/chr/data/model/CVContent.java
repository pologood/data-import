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
 * @date 2016年3月30日 下午11:50:29
 * @see
 * @since
 */
@Alias("CVContent")
public class CVContent extends CoId {

	private static final long serialVersionUID = 3312119108239283878L;

	@NotBlank(message = "不能为空")
	@Length(min = 1000, max = Integer.MAX_VALUE, message = "建立长度过低了")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return JSONUtils.writeValue(this);
	}

}
