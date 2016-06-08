package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * JobTypeBo -- for期望职位
 * @author shigy
 * @date  2015年9月7日
 * 
 */
public class JobTypeForExper {
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String bigId;

	private String bigName;
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String categoryId;
	
	private String categoryName;
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String jobNameId;
	
	private String jobName;

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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getJobNameId() {
		return jobNameId;
	}

	public void setJobNameId(String jobNameId) {
		this.jobNameId = jobNameId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	

}
