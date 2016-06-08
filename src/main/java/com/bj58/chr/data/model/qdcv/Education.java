package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:33:25
 * @see
 * @since
 */
public class Education {

	private long stime;// : "入学时间yyyyMM",
	private long etime;// : "毕业时间yyyyMM",
	@JsonProperty("time_cnt")
	private String timeCnt;// : "教育时间原文",
	private String school;// : "学校名称",
	private String speciality;// : "专业名称",
	private String description;// : "描述",
	private String degree;// : "学位id",
	@JsonProperty("degree_cnt")
	private String degreeCnt;// : "学位原文",
	private String backgd;// : '学历',
	@JsonProperty("backgd_cnt")
	private String backgdCnt;// : "学历原文", // 初中:1 高中:2 中技:3 中专:4 大专:5 本科:6
								// MBA&EMBA:7 硕士:8 博士:9 其他:10
	@JsonProperty("unified-enrollment")
	private String unifiedEnrollment;// : "是否统招，否：0，是：1" // new
	
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
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getDegreeCnt() {
        return degreeCnt;
    }
    public void setDegreeCnt(String degreeCnt) {
        this.degreeCnt = degreeCnt;
    }
    public String getBackgd() {
        return backgd;
    }
    public void setBackgd(String backgd) {
        this.backgd = backgd;
    }
    public String getBackgdCnt() {
        return backgdCnt;
    }
    public void setBackgdCnt(String backgdCnt) {
        this.backgdCnt = backgdCnt;
    }
    public String getUnifiedEnrollment() {
        return unifiedEnrollment;
    }
    public void setUnifiedEnrollment(String unifiedEnrollment) {
        this.unifiedEnrollment = unifiedEnrollment;
    }
	
}
