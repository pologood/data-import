package com.bj58.chr.data.model.qdcv;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月31日 上午12:52:59
 * @see
 * @since
 */
public class MetaInfo {

	@JsonProperty("template_name")
	private String templateName;// "模板名称"
	@JsonProperty("sys_ver")
	private String sysVer;// "解析系统版本，默认201509"
	@JsonProperty("biz_code")
	private String bizCode;// "简历业务来源"
	@JsonProperty("biz_id")
	private String bizId;// "对应业务的简历唯一标识", // 如乔大招 简历id，原网站简历ID(关联ID，暂时留空)
	@JsonProperty("file_path")
	private String filePath;// "文档路径"
	@JsonProperty("org_file_path")
	private String orgFilePath;// "更名前文档路径"
	@JsonProperty("org_name")
	private String orgName;// "原始文档路径"
	@JsonProperty("file_type")
	private String fileType;// "文档类型"
	@JsonProperty("file_size")
	private String fileSize;// "原文件大小", // 原文件大小（字节数）
	@JsonProperty("resume_size")
	private String resumeSize;// 简历内容数据大小（解析结果字符串(resume_info)大小，字节数，为将来比较简历用）
	private String edus;// "教育经历数"
	private String works;// "工作经历数"
	private String projects;// "项目经历数"
	@JsonProperty("resume_tags")
	private List<ResumeTag> resumeTags;//// 标签集合，供检索使用，目前存在7种标签，详见简历合并规则文档。
	private String rate;// "解析比率", // 模板对应的解析比率
	@JsonProperty("parsed_count")
	private String parsedCount;// "已解析字段数量"
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public String getSysVer() {
        return sysVer;
    }
    public void setSysVer(String sysVer) {
        this.sysVer = sysVer;
    }
    public String getBizCode() {
        return bizCode;
    }
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
    public String getBizId() {
        return bizId;
    }
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getOrgFilePath() {
        return orgFilePath;
    }
    public void setOrgFilePath(String orgFilePath) {
        this.orgFilePath = orgFilePath;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileSize() {
        return fileSize;
    }
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    public String getResumeSize() {
        return resumeSize;
    }
    public void setResumeSize(String resumeSize) {
        this.resumeSize = resumeSize;
    }
    public String getEdus() {
        return edus;
    }
    public void setEdus(String edus) {
        this.edus = edus;
    }
    public String getWorks() {
        return works;
    }
    public void setWorks(String works) {
        this.works = works;
    }
    public String getProjects() {
        return projects;
    }
    public void setProjects(String projects) {
        this.projects = projects;
    }
    public List<ResumeTag> getResumeTags() {
        return resumeTags;
    }
    public void setResumeTags(List<ResumeTag> resumeTags) {
        this.resumeTags = resumeTags;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getParsedCount() {
        return parsedCount;
    }
    public void setParsedCount(String parsedCount) {
        this.parsedCount = parsedCount;
    }
	
}
