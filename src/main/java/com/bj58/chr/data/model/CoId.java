package com.bj58.chr.data.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.bj58.chr.common.web.IntPersistable;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 下午5:44:03
 * @see
 * @since
 */
public class CoId extends IntPersistable {

	private static final long serialVersionUID = -4194055064985672201L;

	@NotBlank(message = "不能为空")
	@Length(min = 36, max = 36, message = "合作ID错误，合作ID长度为36")
	private String coid;

	public String getCoid() {
		return coid;
	}

	public void setCoid(String coid) {
		this.coid = coid;
	}

}
