package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:51:57
 * @see
 * @since
 */
public class Training {

	private long stime;// : "培训开始时间yyyyMMdd",
	private long etime;// : "培训结束时间yyyyMMdd",
	@JsonProperty("time_cnt")
	private String timeCnt;// : "培训结束时间原文",
	private String agency;// : "机构名",
	@JsonProperty("cer_name")
	private String cerName;// : "证书名",
	private String course;// : "培训课程",
	private String description;// : "课程描述",
	private String address;// :private Object 培训地点"
    public long getStime() {
        return stime;
    }
    public void setStime(long stime) {
        this.stime = stime;
    }
    public long getEtime() {
        return etime;
    }
    public void setEtime(long etime) {
        this.etime = etime;
    }
    public String getTimeCnt() {
        return timeCnt;
    }
    public void setTimeCnt(String timeCnt) {
        this.timeCnt = timeCnt;
    }
    public String getAgency() {
        return agency;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }
    public String getCerName() {
        return cerName;
    }
    public void setCerName(String cerName) {
        this.cerName = cerName;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
	
}
