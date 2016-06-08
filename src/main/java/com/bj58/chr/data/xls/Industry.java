package com.bj58.chr.data.xls;

import org.hibernate.validator.constraints.NotEmpty;

import com.bj58.chr.common.poi.annotation.ElColumn;
import com.bj58.chr.common.poi.annotation.ElEntity;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月14日 下午5:05:06
 * @see
 * @since
 */
@ElEntity
public class Industry {
	@NotEmpty(message = "不能为空")
	@ElColumn(name = "巧达", index = 0)
	private String qdId;
	@NotEmpty(message = "不能为空")
	@ElColumn(name = "巧达", index = 1)
	private String qdName;
	@NotEmpty(message = "不能为空")
	@ElColumn(name = "巧达", index = 2)
	private String ycId2;
	@ElColumn(name = "巧达", index = 3)
	private String ycId;
	@ElColumn(name = "巧达", index = 4)
	private String ycName2;
	@ElColumn(name = "巧达", index = 5)
	private String ycName;

	public String getYcName2() {
        return ycName2;
    }

    public void setYcName2(String ycName2) {
        this.ycName2 = ycName2;
    }

    public String getQdId() {
		return qdId;
	}

	public void setQdId(String qdId) {
		this.qdId = qdId;
	}

	public String getQdName() {
		return qdName;
	}

	public void setQdName(String qdName) {
		this.qdName = qdName;
	}

	public String getYcId() {
		return ycId;
	}

	public void setYcId(String ycId) {
		this.ycId = ycId;
	}

	public String getYcId2() {
		return ycId2;
	}

	public void setYcId2(String ycId2) {
		this.ycId2 = ycId2;
	}

	public String getYcName() {
		return ycName;
	}

	public void setYcName(String ycName) {
		this.ycName = ycName;
	}

	@Override
	public String toString() {
		return "Industry [qdId=" + qdId + ", qdName=" + qdName + ", ycId=" + ycId + ", ycId2=" + ycId2 + ", ycName="
				+ ycName + "]";
	}

}
