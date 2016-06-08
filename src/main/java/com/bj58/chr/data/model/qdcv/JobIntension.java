package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:55:10
 * @see
 * @since
 */
public class JobIntension {

	private int[] city;// // ["期望工作地"], //======== 需要字典 ========
	@JsonProperty("city_cnt")
	private String[] cityCnt; // ["期望工作地原文"],
	private String state; // 在找工作:1 目前满意，有更好的工作可以考虑:2 暂时不想找工作:3
	@JsonProperty("state_cnt")
	private String stateCnt; // private Object 状态原文",
	@JsonProperty("job_nature")
	private String jobNature;// 期望工作性质", //全职:1 兼职:2 实习:3 全/兼职:4
	@JsonProperty("job_nature_cnt")
	private String jobNatureCnt;// private Object 期望工作性质原文",
	private String[] job; // ["期望工作"], //======== 需要字典职位 ========
	@JsonProperty("job_cnt")
	private String[] jobCnt; // ["期望工作原文"],
	private String[] trade; // ["期望行业"], //======== 需要字典行业 ========
	@JsonProperty("trade_cnt")
	private String[] tradeCnt; // ["期望行业原文"],
	@JsonProperty("arrival_time")
	private String arrivalTime;// private Object 到岗时间", //需要对行业字典
	@JsonProperty("arrival_cnt")
	private String arrivalCnt; // '到岗时间原文'
	@JsonProperty("company_type")
	private String companyType;// private Object 期望单位性质",
	@JsonProperty("company_type_cnt")
	private String companyTypeCnt;// private Object 期望单位性质原文",
	@JsonProperty("cur_salary_from")
	private String curSalaryFrom;// private Object 当前收入（低限）",
	@JsonProperty("cur_salary_to")
	private String curSalaryTo;// private Object 当前收入（高限）",
	@JsonProperty("cur_salary_cnt")
	private String curSalaryCnt;// private Object 当前收入原文",
	@JsonProperty("hope_salary_from")
	private String hopeSalaryFrom;// private Object 期望工作薪资，起点",
	@JsonProperty("hope_salary_to")
	private String hopeSalaryTo;// private Object 期望工作薪资，高点。",
	@JsonProperty("hope_salary_cnt")
	private String hopeSalaryCnt;// "期望收入原文"
    public int[] getCity() {
        return city;
    }
    public void setCity(int[] city) {
        this.city = city;
    }
    public String[] getCityCnt() {
        return cityCnt;
    }
    public void setCityCnt(String[] cityCnt) {
        this.cityCnt = cityCnt;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStateCnt() {
        return stateCnt;
    }
    public void setStateCnt(String stateCnt) {
        this.stateCnt = stateCnt;
    }
    public String getJobNature() {
        return jobNature;
    }
    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }
    public String getJobNatureCnt() {
        return jobNatureCnt;
    }
    public void setJobNatureCnt(String jobNatureCnt) {
        this.jobNatureCnt = jobNatureCnt;
    }
    public String[] getJob() {
        return job;
    }
    public void setJob(String[] job) {
        this.job = job;
    }
    public String[] getJobCnt() {
        return jobCnt;
    }
    public void setJobCnt(String[] jobCnt) {
        this.jobCnt = jobCnt;
    }
    public String[] getTrade() {
        return trade;
    }
    public void setTrade(String[] trade) {
        this.trade = trade;
    }
    public String[] getTradeCnt() {
        return tradeCnt;
    }
    public void setTradeCnt(String[] tradeCnt) {
        this.tradeCnt = tradeCnt;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getArrivalCnt() {
        return arrivalCnt;
    }
    public void setArrivalCnt(String arrivalCnt) {
        this.arrivalCnt = arrivalCnt;
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
    public String getCurSalaryFrom() {
        return curSalaryFrom;
    }
    public void setCurSalaryFrom(String curSalaryFrom) {
        this.curSalaryFrom = curSalaryFrom;
    }
    public String getCurSalaryTo() {
        return curSalaryTo;
    }
    public void setCurSalaryTo(String curSalaryTo) {
        this.curSalaryTo = curSalaryTo;
    }
    public String getCurSalaryCnt() {
        return curSalaryCnt;
    }
    public void setCurSalaryCnt(String curSalaryCnt) {
        this.curSalaryCnt = curSalaryCnt;
    }
    public Object getHopeSalaryFrom() {
        return hopeSalaryFrom;
    }
    public void setHopeSalaryFrom(String hopeSalaryFrom) {
        this.hopeSalaryFrom = hopeSalaryFrom;
    }
    public Object getHopeSalaryTo() {
        return hopeSalaryTo;
    }
    public void setHopeSalaryTo(String hopeSalaryTo) {
        this.hopeSalaryTo = hopeSalaryTo;
    }
    public String getHopeSalaryCnt() {
        return hopeSalaryCnt;
    }
    public void setHopeSalaryCnt(String hopeSalaryCnt) {
        this.hopeSalaryCnt = hopeSalaryCnt;
    }
	
}
