package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 培训经历实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class TrainingsBo extends BaseBo{

	@NotNull
	private String id;
	
	private String course;
	
	private String school;
	
	@Min(-28800)
	private Long start;
	
	private Long end;
	
	private String desc;
	
	private String isDel;
	
	private Long delTime;
	
	private Long addTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
	    if(end <= 0L){
	        end = getAsYetDate();
	    }
		this.end = end;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
