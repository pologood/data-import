package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:54:41
 * @see
 * @since
 */
public class PositionInfo {

	private String company; // "公司名称",
	private String position; // "职位名称",
	private String location; // "工作地点",
	@JsonProperty("location_cnt")
	private String locationCnt;// "工作地点原文"
	
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocationCnt() {
        return locationCnt;
    }
    public void setLocationCnt(String locationCnt) {
        this.locationCnt = locationCnt;
    }
	
}
