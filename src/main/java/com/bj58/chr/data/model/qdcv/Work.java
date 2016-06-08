package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:48:21
 * @see
 * @since
 */
public class Work {

	private long stime;// : "入职时间yyyyMMdd",
	private long etime;// : "离职时间yyyyMMdd",
	@JsonProperty("time_cnt")
	private String timeCnt;// : "工作时间原文",
	private String company;// : "公司名称",
	@JsonProperty("company_type")
	private String companyType;// : "公司性质", //======== 需要字典确认公司类型 ========
	@JsonProperty("company_type_cnt")
	private String companyTypeCnt;// : "公司性质原文",
	@JsonProperty("company_disc")
	private String companyDisc;// : "公司描述", // new
	private String location;// : "所在地区", // new
	private String department;// : "部门",
	private String trade;// : "行业", //======== 需要字典确认行业 ========
	@JsonProperty("trade_cnt")
	private String tradeCnt;// : "行业原文",
	private String job;// : "工作",
	@JsonProperty("job_description")
	private String jobDescription;// : "工作描述",
	@JsonProperty("salary_cnt")
	private String salaryCnt;// : "薪资原文",
	private String achievements;// : "业绩",
	@JsonProperty("report_to")
	private String reportTo;// : "汇报对象",
	private String subordinate;// : "下属",
	@JsonProperty("im_subordinate")
	private String imSubordinate;// : "直接下属",
	private String duty;// : "职责",
	@JsonProperty("company_size_cnt")
	private String companySizeCnt;// : "公司规模原文",
	@JsonProperty("position_type")
	private String positionType;// : "职位类别", // new ??? 什么值？
	@JsonProperty("reason_of_quit")
	private String reasonOfQuit;// : "离职原因",
	@JsonProperty("salary_from")
	private String salaryFrom;// :
	@JsonProperty("salary_to")
	private String salaryTo;// :
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
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    public String getCompanyTypeCnt() {
        return companyTypeCnt;
    }
    public void setCompanyTypeCnt(String companyTypeCnt) {
        this.companyTypeCnt = companyTypeCnt;
    }
    public String getCompanyDisc() {
        return companyDisc;
    }
    public void setCompanyDisc(String companyDisc) {
        this.companyDisc = companyDisc;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getTrade() {
        return trade;
    }
    public void setTrade(String trade) {
        this.trade = trade;
    }
    public String getTradeCnt() {
        return tradeCnt;
    }
    public void setTradeCnt(String tradeCnt) {
        this.tradeCnt = tradeCnt;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    public String getSalaryCnt() {
        return salaryCnt;
    }
    public void setSalaryCnt(String salaryCnt) {
        this.salaryCnt = salaryCnt;
    }
    public String getAchievements() {
        return achievements;
    }
    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
    public String getReportTo() {
        return reportTo;
    }
    public void setReportTo(String reportTo) {
        this.reportTo = reportTo;
    }
    public String getSubordinate() {
        return subordinate;
    }
    public void setSubordinate(String subordinate) {
        this.subordinate = subordinate;
    }
    public String getImSubordinate() {
        return imSubordinate;
    }
    public void setImSubordinate(String imSubordinate) {
        this.imSubordinate = imSubordinate;
    }
    public String getDuty() {
        return duty;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }
    public String getCompanySizeCnt() {
        return companySizeCnt;
    }
    public void setCompanySizeCnt(String companySizeCnt) {
        this.companySizeCnt = companySizeCnt;
    }
    public String getPositionType() {
        return positionType;
    }
    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }
    public String getReasonOfQuit() {
        return reasonOfQuit;
    }
    public void setReasonOfQuit(String reasonOfQuit) {
        this.reasonOfQuit = reasonOfQuit;
    }
    public String getSalaryFrom() {
        return salaryFrom;
    }
    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }
    public String getSalaryTo() {
        return salaryTo;
    }
    public void setSalaryTo(String salaryTo) {
        this.salaryTo = salaryTo;
    }
	
}
