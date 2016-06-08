package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年3月9日 下午12:20:14
 * @see
 * @since
 */
@Document(collection = "cvInfoBo")
public class CvInfoBo extends BaseBo {

	@Pattern(regexp = "[A-Za-z0-9\\-]{1,}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String id;

	private String type;

	/**
	 * 是否已删除
	 */
	private String isDel;

	/**
	 * 删除时间
	 */
	private long delTime;

	/**
	 * 修改时间
	 */
	private long lModTime;

	private String uid;

	private String tempId;

	private String pid;

	/**
	 * 是否英文简历 (1-是 0-否)
	 */
	private String isEnCV;

	private String cvName;

	private String verified;

	private String unCheckReason;

	private long verifyTime;

	private String lang;

	private String visibility;

	private String isAutoRefresh;

	private long views;

	private long downloads;

	private String isFileVerified;

	/**
	 * 中文名称
	 */
	private String cnName;

	/**
	 * 英文名称
	 */
	private String enName;

	private String gender;

	/**
	 * 生日
	 */
	private long birthday;

	/**
	 * 学历id
	 */
	@NotNull
	private String degreeId;

	/**
	 * 学历名称
	 */
	private String degreeName;

	private String yearsId;

	private String years;

	private String photoPath;

	private String idType;

	private String idName;

	private String idNumbers;

	private String marry;

	private String nationId;

	private String nation;

	/**
	 * 国籍编号
	 */
	private String nationalityId;

	/**
	 * 国籍
	 */
	private String nationality;

	private Double height;

	private Double weight;

	private String email;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 移动电话
	 */
	private String phone;

	private String weibo;

	private String qq;

	private String weixin;

	/**
	 * 工作状态
	 */
	private String workStatus;

	private String onboardTime;

	/**
	 * 自我评价
	 */
	private String selfEval;

	private String isOverseas;

	private String osExperienceId;

	private String osExperience;

	private String jobAlert;

	private String ranks;

	private String isAutoRank;

	/**
	 * 最后次刷新时间
	 */
	private long lRefTime;

	/**
	 * 最后次修改时间
	 */
	private long lastModTime;

	/**
	 * 是否完整(1-完整 0-不完整)
	 */
	private String isCompleted;

	private String completedProgress;

	/**
	 * 完整率
	 */
	private Double percent;

	/**
	 * 新增时间
	 */
	private long addTime;

	private long hiddenMonth;

	private long expSalary;

	@Pattern(regexp = "\\d+", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String expJobTypeId;

	private String expJobTypeName;

	@Pattern(regexp = "\\d+", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String expComTypeId;

	private String expComTypeName;

	private String[] expJobName;

	private String negotiation;

	/**
	 * 导入来源(老字段)
	 */
	private String mark;

	/**
	 * 亮点介绍
	 */
	private String wonderfullPoint;

	private long fails;

	private long point;

	private String isRemind;

	/**
	 * 
	 */
	private String status;

	/**
	 * 
	 */
	private long workTime;

	private long isWork;

	private String drivId;

	private String drivName;

	private RegionalismBo living;

	private RegionalismBo domicile;

	private OtherInfoBo otherInfo;

	/**
	 * 期望行业
	 */
	private List<IndustryBo> expIndustry;

	/**
	 * 期望地域
	 */
	private List<RegionalismBo> expLocation;

	/**
	 * 期望职位
	 */
	private List<JobTypeForExper> expJobs;

	/**
	 * 教育经历
	 */
	private List<EducationsBo> education;

	/**
	 * 工作经历
	 */
	private List<ExperiencesBo> experience;

	/**
	 * 语言技能
	 */
	private List<LangSkillsBo> langSkills;

	/**
	 * it技能
	 */
	private List<ITSkillsBo> itSkills;

	/**
	 * 培训经历
	 */
	private List<TrainingsBo> training;

	/**
	 * 证书
	 */
	private List<CertsBo> certs;

	/**
	 * 项目经历
	 */
	private List<ProjectsBo> projects;

	/**
	 * 简历类型(0-标准 1-附件)
	 */
	@NotNull
	private Long classify;

	private ExperiencesBo lastExperience;

	/**
	 * 
	 */
	private AttachCVBo attachCV;

	private String resumeId;

	/**
	 * 第三方渠道导入id
	 */
	private String infoId;

	/**
	 * 简历来源 (1-58导入 2-m端 3-pc 4-app)
	 */
	private Long src;

	public Long getSrc() {
		return src;
	}

	public void setSrc(Long src) {
		this.src = src;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public long getDelTime() {
		return delTime;
	}

	public void setDelTime(long delTime) {
		this.delTime = delTime;
	}

	public long getLModTime() {
		return lModTime;
	}

	public void setLModTime(long lModTime) {
		this.lModTime = lModTime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIsEnCV() {
		return isEnCV;
	}

	public void setIsEnCV(String isEnCV) {
		this.isEnCV = isEnCV;
	}

	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getUnCheckReason() {
		return unCheckReason;
	}

	public void setUnCheckReason(String unCheckReason) {
		this.unCheckReason = unCheckReason;
	}

	public Long getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(long verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getIsAutoRefresh() {
		return isAutoRefresh;
	}

	public void setIsAutoRefresh(String isAutoRefresh) {
		this.isAutoRefresh = isAutoRefresh;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Long getDownloads() {
		return downloads;
	}

	public void setDownloads(Long downloads) {
		this.downloads = downloads;
	}

	public String getIsFileVerified() {
		return isFileVerified;
	}

	public void setIsFileVerified(String isFileVerified) {
		this.isFileVerified = isFileVerified;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
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

	public String getYearsId() {
		return yearsId;
	}

	public void setYearsId(String yearsId) {
		this.yearsId = yearsId;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getIdNumbers() {
		return idNumbers;
	}

	public void setIdNumbers(String idNumbers) {
		this.idNumbers = idNumbers;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getNationId() {
		return nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getOnboardTime() {
		return onboardTime;
	}

	public void setOnboardTime(String onboardTime) {
		this.onboardTime = onboardTime;
	}

	public String getSelfEval() {
		return selfEval;
	}

	public void setSelfEval(String selfEval) {
		this.selfEval = selfEval;
	}

	public String getIsOverseas() {
		return isOverseas;
	}

	public void setIsOverseas(String isOverseas) {
		this.isOverseas = isOverseas;
	}

	public String getOsExperienceId() {
		return osExperienceId;
	}

	public void setOsExperienceId(String osExperienceId) {
		this.osExperienceId = osExperienceId;
	}

	public String getOsExperience() {
		return osExperience;
	}

	public void setOsExperience(String osExperience) {
		this.osExperience = osExperience;
	}

	public String getJobAlert() {
		return jobAlert;
	}

	public void setJobAlert(String jobAlert) {
		this.jobAlert = jobAlert;
	}

	public String getRanks() {
		return ranks;
	}

	public void setRanks(String ranks) {
		this.ranks = ranks;
	}

	public String getIsAutoRank() {
		return isAutoRank;
	}

	public void setIsAutoRank(String isAutoRank) {
		this.isAutoRank = isAutoRank;
	}

	public long getLRefTime() {
		return lRefTime;
	}

	public void setLRefTime(long lRefTime) {
		this.lRefTime = lRefTime;
	}

	public long getLastModTime() {
		return lastModTime;
	}

	public void setLastModTime(long lastModTime) {
		this.lastModTime = lastModTime;
	}

	public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getCompletedProgress() {
		return completedProgress;
	}

	public void setCompletedProgress(String completedProgress) {
		this.completedProgress = completedProgress;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public Long getHiddenMonth() {
		return hiddenMonth;
	}

	public void setHiddenMonth(Long hiddenMonth) {
		this.hiddenMonth = hiddenMonth;
	}

	public Long getExpSalary() {
		return expSalary;
	}

	public void setExpSalary(long expSalary) {
		this.expSalary = expSalary;
	}

	public String getExpJobTypeId() {
		return expJobTypeId;
	}

	public void setExpJobTypeId(String expJobTypeId) {
		this.expJobTypeId = expJobTypeId;
	}

	public String getExpJobTypeName() {
		return expJobTypeName;
	}

	public void setExpJobTypeName(String expJobTypeName) {
		this.expJobTypeName = expJobTypeName;
	}

	public String getExpComTypeId() {
		return expComTypeId;
	}

	public void setExpComTypeId(String expComTypeId) {
		this.expComTypeId = expComTypeId;
	}

	public String getExpComTypeName() {
		return expComTypeName;
	}

	public void setExpComTypeName(String expComTypeName) {
		this.expComTypeName = expComTypeName;
	}

	public String[] getExpJobName() {
		return expJobName;
	}

	public void setExpJobName(String[] expJobName) {
		this.expJobName = expJobName;
	}

	public String getNegotiation() {
		return negotiation;
	}

	public void setNegotiation(String negotiation) {
		this.negotiation = negotiation;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Long getFails() {
		return fails;
	}

	public void setFails(Long fails) {
		this.fails = fails;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(String isRemind) {
		this.isRemind = isRemind;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getWorkTime() {
		return workTime;
	}

	public void setWorkTime(long workTime) {
		this.workTime = workTime;
	}

	public long getIsWork() {
	    if(workTime > 0){
	        return 1;
	    }
	    return 0;
	}

	public void setIsWork(long isWork) {
		this.isWork = isWork;
	}

	public String getDrivId() {
		return drivId;
	}

	public void setDrivId(String drivId) {
		this.drivId = drivId;
	}

	public String getDrivName() {
		return drivName;
	}

	public void setDrivName(String drivName) {
		this.drivName = drivName;
	}

	public RegionalismBo getLiving() {
		return living;
	}

	public void setLiving(RegionalismBo living) {
		this.living = living;
	}

	public RegionalismBo getDomicile() {
		return domicile;
	}

	public void setDomicile(RegionalismBo domicile) {
		this.domicile = domicile;
	}

	public OtherInfoBo getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(OtherInfoBo otherInfo) {
		this.otherInfo = otherInfo;
	}

	public java.util.List<IndustryBo> getExpIndustry() {
		return expIndustry;
	}

	public void setExpIndustry(java.util.List<IndustryBo> expIndustry) {
		this.expIndustry = expIndustry;
	}

	public java.util.List<RegionalismBo> getExpLocation() {
		return expLocation;
	}

	public void setExpLocation(java.util.List<RegionalismBo> expLocation) {
		this.expLocation = expLocation;
	}

	public java.util.List<JobTypeForExper> getExpJobs() {
		return expJobs;
	}

	public void setExpJobs(java.util.List<JobTypeForExper> expJobs) {
		this.expJobs = expJobs;
	}

	public java.util.List<EducationsBo> getEducation() {
		return education;
	}

	public void setEducation(java.util.List<EducationsBo> education) {
		this.education = education;
	}

	public java.util.List<ExperiencesBo> getExperience() {
		return experience;
	}

	public void setExperience(java.util.List<ExperiencesBo> experience) {
		this.experience = experience;
	}

	public java.util.List<LangSkillsBo> getLangSkills() {
		return langSkills;
	}

	public void setLangSkills(java.util.List<LangSkillsBo> langSkills) {
		this.langSkills = langSkills;
	}

	public java.util.List<ITSkillsBo> getItSkills() {
		return itSkills;
	}

	public void setItSkills(java.util.List<ITSkillsBo> itSkills) {
		this.itSkills = itSkills;
	}

	public java.util.List<TrainingsBo> getTraining() {
		return training;
	}

	public void setTraining(java.util.List<TrainingsBo> training) {
		this.training = training;
	}

	public java.util.List<CertsBo> getCerts() {
		return certs;
	}

	public void setCerts(java.util.List<CertsBo> certs) {
		this.certs = certs;
	}

	public java.util.List<ProjectsBo> getProjects() {
		return projects;
	}

	public void setProjects(java.util.List<ProjectsBo> projects) {
		this.projects = projects;
	}

	public Long getClassify() {
		return classify;
	}

	public void setClassify(Long classify) {
		this.classify = classify;
	}

	public ExperiencesBo getLastExperience() {
		return lastExperience;
	}

	public void setLastExperience(ExperiencesBo lastExperience) {
		this.lastExperience = lastExperience;
	}

	public AttachCVBo getAttachCV() {
		return attachCV;
	}

	public void setAttachCV(AttachCVBo attachCV) {
		this.attachCV = attachCV;
	}

	public String getResumeId() {
		return resumeId;
	}

	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getWonderfullPoint() {
		return wonderfullPoint;
	}

	public void setWonderfullPoint(String wonderfullPoint) {
		this.wonderfullPoint = wonderfullPoint;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Override
	public String toString() {
		return "CvInfoBo [id=" + id + ", type=" + type + ", isDel=" + isDel + ", delTime=" + delTime + ", lModTime="
				+ lModTime + ", uid=" + uid + ", tempId=" + tempId + ", pid=" + pid + ", isEnCV=" + isEnCV + ", cvName="
				+ cvName + ", verified=" + verified + ", unCheckReason=" + unCheckReason + ", verifyTime=" + verifyTime
				+ ", lang=" + lang + ", visibility=" + visibility + ", isAutoRefresh=" + isAutoRefresh + ", views="
				+ views + ", downloads=" + downloads + ", isFileVerified=" + isFileVerified + ", cnName=" + cnName
				+ ", enName=" + enName + ", gender=" + gender + ", birthday=" + birthday + ", degreeId=" + degreeId
				+ ", degreeName=" + degreeName + ", yearsId=" + yearsId + ", years=" + years + ", photoPath="
				+ photoPath + ", idType=" + idType + ", idName=" + idName + ", idNumbers=" + idNumbers + ", marry="
				+ marry + ", nationId=" + nationId + ", nation=" + nation + ", nationalityId=" + nationalityId
				+ ", nationality=" + nationality + ", height=" + height + ", weight=" + weight + ", email=" + email
				+ ", mobile=" + mobile + ", phone=" + phone + ", weibo=" + weibo + ", qq=" + qq + ", weixin=" + weixin
				+ ", workStatus=" + workStatus + ", onboardTime=" + onboardTime + ", selfEval=" + selfEval
				+ ", isOverseas=" + isOverseas + ", osExperienceId=" + osExperienceId + ", osExperience=" + osExperience
				+ ", jobAlert=" + jobAlert + ", ranks=" + ranks + ", isAutoRank=" + isAutoRank + ", lRefTime="
				+ lRefTime + ", lastModTime=" + lastModTime + ", isCompleted=" + isCompleted + ", completedProgress="
				+ completedProgress + ", percent=" + percent + ", addTime=" + addTime + ", hiddenMonth=" + hiddenMonth
				+ ", expSalary=" + expSalary + ", expJobTypeId=" + expJobTypeId + ", expJobTypeName=" + expJobTypeName
				+ ", expComTypeId=" + expComTypeId + ", expComTypeName=" + expComTypeName + ", expJobName="
				+ Arrays.toString(expJobName) + ", negotiation=" + negotiation + ", mark=" + mark + ", wonderfullPoint="
				+ wonderfullPoint + ", fails=" + fails + ", point=" + point + ", isRemind=" + isRemind + ", status="
				+ status + ", workTime=" + workTime + ", isWork=" + isWork + ", drivId=" + drivId + ", drivName="
				+ drivName + ", living=" + living + ", domicile=" + domicile + ", otherInfo=" + otherInfo
				+ ", expIndustry=" + expIndustry + ", expLocation=" + expLocation + ", expJobs=" + expJobs
				+ ", education=" + education + ", experience=" + experience + ", langSkills=" + langSkills
				+ ", itSkills=" + itSkills + ", training=" + training + ", certs=" + certs + ", projects=" + projects
				+ ", classify=" + classify + ", lastExperience=" + lastExperience + ", attachCV=" + attachCV
				+ ", resumeId=" + resumeId + ", infoId=" + infoId + ", src=" + src + "]";
	}

}
