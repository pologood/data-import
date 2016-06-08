package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:36:19
 * @see
 * @since
 */
public class Project {

	private long stime;// : "项目开始时间 yyyyMMdd",
	private long etime;// : "项目结束时间 yyyyMMdd",
	@JsonProperty("time_cnt")
	private String time_cnt;// : "工作时间原文",
	@JsonProperty("cur_company")
	private String curCompany;// : "所在公司", // new
	private String position;// : "项目职务", // new
	private String achievement;// : "项目业绩", // new
	@JsonProperty("project_name")
	private String projectName;// : "项目名",
	private String description;// : "项目描述",
	private String duty;// : "职责描述",
	@JsonProperty("env_software")
	private String envSoftware;// : "软件开发环境",
	@JsonProperty("env_hardware")
	private String envHardware;// : "硬件开发环境",
	@JsonProperty("dev_tools")
	private String devTools;// : "开发工具"
	
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
    public String getTime_cnt() {
        return time_cnt;
    }
    public void setTime_cnt(String time_cnt) {
        this.time_cnt = time_cnt;
    }
    public String getCurCompany() {
        return curCompany;
    }
    public void setCurCompany(String curCompany) {
        this.curCompany = curCompany;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getAchievement() {
        return achievement;
    }
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDuty() {
        return duty;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }
    public String getEnvSoftware() {
        return envSoftware;
    }
    public void setEnvSoftware(String envSoftware) {
        this.envSoftware = envSoftware;
    }
    public String getEnvHardware() {
        return envHardware;
    }
    public void setEnvHardware(String envHardware) {
        this.envHardware = envHardware;
    }
    public String getDevTools() {
        return devTools;
    }
    public void setDevTools(String devTools) {
        this.devTools = devTools;
    }
	
}
