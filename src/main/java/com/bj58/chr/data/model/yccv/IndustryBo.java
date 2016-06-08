package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.Pattern;

/**
 * 行业实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class IndustryBo{
    
    @Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String bigId;
	
	private String bigName;
	
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String induId;
	
	private String induName;
	
	public String getBigId() {
		return bigId;
	}
	public void setBigId(String bigId) {
		this.bigId = bigId;
	}
	public String getBigName() {
		return bigName;
	}
	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
	public String getInduId() {
		return induId;
	}
	public void setInduId(String induId) {
		this.induId = induId;
	}
	public String getInduName() {
		return induName;
	}
	public void setInduName(String induName) {
		this.induName = induName;
	}
}
