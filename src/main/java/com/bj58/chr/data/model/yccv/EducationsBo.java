package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 教育经历实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class EducationsBo extends BaseBo{
	
    @NotNull
	private String id;
	
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String degreeId;
	 
	private String degreeName;
	 
	private String college;
	 
	private String major;
	
	@Min(-28800)
	private long start;
	 
	private long end;
	 
	private String description;
	 
	private String isDel;
	 
	private Long delTime;
	 
	private Long addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Long getDelTime() {
		return delTime;
	}

	public void setDelTime(Long delTime) {
		this.delTime = delTime;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

}
