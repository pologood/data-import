package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 项目经验实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class ProjectsBo extends BaseBo{
	@NotNull
	private String id;
	
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 职责描述
	 */
	private String resp;
	
	@Min(-28800)
	private long start;
	
	private long end;
	
	/**
	 * 项目描述
	 */
	private String projDesc;
	
	private String isDel;
	
	private long delTime;
	
	private long addTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
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
	public String getProjDesc() {
		return projDesc;
	}
	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
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
