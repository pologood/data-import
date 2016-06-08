package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 工作经历实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class ExperiencesBo extends BaseBo{
	
    @NotNull
	private String id;
	
	private String comName;
	
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String comTypeId;
	
	private Integer isInternship;

	private String comType;
	
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String bigId;
	
	private String bigName;
	
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String jobTypeId;
	
	private String jobType;
	
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String jobNameId;
	
	private String jobName;
	
	private String inputJobName;
	
	private long start;
	
	private long end;
	
	private String salaryId;
	
	private String salary;
	
	private String comSizeId;
	
	private String comSize;
	
	private String subordinates;

	private String department;
	
	private String workDesc;

	private Long addTime;
	
	private java.util.List<IndustryBo> industry;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComTypeId() {
		return comTypeId;
	}

	public void setComTypeId(String comTypeId) {
		this.comTypeId = comTypeId;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

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

	public String getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(String jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
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

	public String getInputJobName() {
		return inputJobName;
	}

	public void setInputJobName(String inputJobName) {
		this.inputJobName = inputJobName;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
	    if(end <= 0L){
    	   end = getAsYetDate();    
	    }
		this.end = end;
	}

	public String getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getComSizeId() {
		return comSizeId;
	}

	public void setComSizeId(String comSizeId) {
		this.comSizeId = comSizeId;
	}

	public String getComSize() {
		return comSize;
	}

	public void setComSize(String comSize) {
		this.comSize = comSize;
	}

	public String getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(String subordinates) {
		this.subordinates = subordinates;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public java.util.List<IndustryBo> getIndustry() {
		return industry;
	}

	public void setIndustry(java.util.List<IndustryBo> industry) {
		this.industry = industry;
	}

	public Integer getIsInternship() {
		return isInternship;
	}

	public void setIsInternship(Integer isInternship) {
		this.isInternship = isInternship;
	}

}
