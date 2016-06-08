package com.bj58.chr.data.model.qdcv;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:28:02
 * @see
 * @since
 */
public class ResumeInfo {

	private String rname;// : "简历名称", f
	private String name;// : "姓名",
	private String nickname;// : "昵称",
	@JsonProperty("create_time")
	private long createTime;//创建时间
    private int gender;// : "性别", // 男:1 女:2
	@JsonProperty("gender_cnt")
	private String genderCnt;// : "性别原文",
	private long birthday;// : "生日(yyyyMMdd/yyyyMM)",
	@JsonProperty("birthday_cnt")
	private String birthdayCnt;// : "生日原文",
	@JsonProperty("cur_city_id")
	@NotNull
    @Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String curCityId;// : "城市的id", //城市的id ======== 需要字典区域 ========
	@JsonProperty("cur_city_cnt")
	private String curCityCnt;// : "城市原文",
	private String mobilephone;// : "手机号",
	private String telephone;// : "电话号(固定电话)",
	private String email;// : "邮箱",
	@JsonProperty("politics_stat")
	private int politicsStat;// 中共党员(含预备党员):1， 团员:2， 群众:3， 民主党派:4， 无党派人士:5，
								// 无可奉告:6
	@JsonProperty("politics_stat_cnt")
	private String politicsStatCnt;// : "政治面貌原文",
	private String hukou;// : "户籍ID", //======== 需要字典区域 ========
	@JsonProperty("hukou_cnt")
	private String hukouCnt;// : "户籍原文",
	@JsonProperty("native_place")
	@NotNull
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String nativePlace;// : "籍贯ID", //======== 需要字典区域 ========
	@JsonProperty("native_cnt")
	private String nativeCnt;// : "籍贯原文",
	private String nation;// : "民族", // 中文
	@JsonProperty("nation_cnt")
	private String nationCnt;// : "民族原文",
	private String nationality;// : "国籍", // 中文
	@JsonProperty("nationality_cnt")
	private String nationalityCnt;// : "国籍原文", // 中文
	private String address;// : "详细地址",
	private String postcode;// : "居住地邮编",
	private String qq;// : "qq号",
	private String weixin;// : "微信号",
	@JsonProperty("marriage_stat")
	private String marriageStat;// : "婚否", // 未婚:1 已婚:2 保密:3 离异：4
	@JsonProperty("marriage_cnt")
	private String marriageCnt;// : "婚否原文",
	@JsonProperty("doc_id_type")
	private String docIdType;// : "证件id类型", // 身份证:1 护照:2 军人证:3 其他:4 香港身份证:5
	@JsonProperty("doc_id_cnt")
	private String docIdCnt;// : "证件原文",
	@JsonProperty("doc_id")
	private String docId;// : "证件号码",
	@JsonProperty("child_count")
	private int childCount;// : "子女数量",
	private int height;// : "身高", // 厘米
	private int weight;// : "体重", // 公斤
	private int backgd;// : "最高学历", // 初中:1 高中:2 中技:3 中专:4 大专:5 本科:6
							// MBA&EMBA:7 硕士:8 博士:9 其他:10
	@JsonProperty("backgd_cnt")
	private String backgdCnt;// : "最高学历原文",
	@JsonProperty("highest_speciality")
	private String highestSpeciality;// : "最高学历专业",
	@JsonProperty("highest_school")
	private String highestSchool;// : "最高学历学校",
	@JsonProperty("study_abroad")
	private int studyAbroad;// : "是否有海外求学经历 0无 1有", //0无 1有
	@JsonProperty("work_abroad")
	private int workAbroad;// : "是否有海外工作经历 0无 1有", //0无 1有
	private String website;// : "个人展示主页URL",
	@JsonProperty("fst_job_date")
	private long fstJobDate;// : "第一份工作开始时间 yyyyMMdd",
	@JsonProperty("fst_job_date_cnt")
	private String fstJobDateCnt;// : "第一份工作开始时间原值",
	@JsonProperty("lst_job_date")
	private long lstJobDate;// : "最近一份工作离职时间 yyyyMMdd", // 0 表示至今
	@JsonProperty("lst_job_date_cnt")
	private String lstJobDateCnt;// : "最近一份工作离职时间原文",
	@JsonProperty("driving_lic")
	private String drivingLic;// : "驾照信息",
	@JsonProperty("ext_message")
	private String extMessage;// : "附加信息",
	@JsonProperty("ext_url")
	private String extUrl;// : "附件简历URL",
	@JsonProperty("is_english")
	private int isEnglish;// : "是否是英文简历", //0否 1是
	@JsonProperty("manage_skill")
	private int manageSkill;// : "是否有管理经验", //0否 1是
	@JsonProperty("logo_path")
	private String logoPath;// : "头像地址",

	private List<Appendix> appendix;

	private List<Certification> certification;
	@JsonProperty("certification_cnt")
	private String certificationCnt;// : "证书信息原文",
	@JsonProperty("self_assessment")
	private List<SelfAssessment> selfAssessment;
	private List<Education> education;
	@JsonProperty("education_cnt")
	private String education_cnt;// : "教育经历集合原文",
	private List<Language> language;
	@JsonProperty("language_cnt")
	private String language_cnt;// : "语言能力原文",

	private List<Project> projects;
	@JsonProperty("projects_cnt")
	private List<ProjectsCnt> projectsCnt;
	private List<Work> work;
	@JsonProperty("work_cnt")
	private List<WorkCnt> workCnt;
	private List<Skill> skills;
	@JsonProperty("skills_cnt")
	private String skillsCnt;
	private List<Training> training;
	@JsonProperty("training_cnt")
	private String trainingCnt;// : "培训经历原文",
	@JsonProperty("hands-on_exp")
	private String handsNnExp;// : "在校实践经验，原文", // new
	@JsonProperty("awards_in_schl")
	private String awardsInSchl;// : "所获奖项（在校学习情况），原文", // new
	private String interest;// : "兴趣爱好，原文", // new
	private String recommendation;// : "推荐信，原文;//// new
	
    public String getAwardsInSchl() {
        return awardsInSchl;
    }
    public void setAwardsInSchl(String awardsInSchl) {
        this.awardsInSchl = awardsInSchl;
    }
    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }
    public String getRecommendation() {
        return recommendation;
    }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    public String getRname() {
        return rname;
    }
    public void setRname(String rname) {
        this.rname = rname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getGenderCnt() {
        return genderCnt;
    }
    public void setGenderCnt(String genderCnt) {
        this.genderCnt = genderCnt;
    }
    public long getBirthday() {
        return birthday;
    }
    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }
    public String getBirthdayCnt() {
        return birthdayCnt;
    }
    public void setBirthdayCnt(String birthdayCnt) {
        this.birthdayCnt = birthdayCnt;
    }
    public String getCurCityId() {
        return curCityId;
    }
    public void setCurCityId(String curCityId) {
        this.curCityId = curCityId;
    }
    public String getCurCityCnt() {
        return curCityCnt;
    }
    public void setCurCityCnt(String curCityCnt) {
        this.curCityCnt = curCityCnt;
    }
    public String getMobilephone() {
        return mobilephone;
    }
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPoliticsStat() {
        return politicsStat;
    }
    public void setPoliticsStat(int politicsStat) {
        this.politicsStat = politicsStat;
    }
    public String getPoliticsStatCnt() {
        return politicsStatCnt;
    }
    public void setPoliticsStatCnt(String politicsStatCnt) {
        this.politicsStatCnt = politicsStatCnt;
    }
    public String getHukou() {
        return hukou;
    }
    public void setHukou(String hukou) {
        this.hukou = hukou;
    }
    public String getHukouCnt() {
        return hukouCnt;
    }
    public void setHukouCnt(String hukouCnt) {
        this.hukouCnt = hukouCnt;
    }
    public String getNativePlace() {
        return nativePlace;
    }
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    public String getNativeCnt() {
        return nativeCnt;
    }
    public void setNativeCnt(String nativeCnt) {
        this.nativeCnt = nativeCnt;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getNationCnt() {
        return nationCnt;
    }
    public void setNationCnt(String nationCnt) {
        this.nationCnt = nationCnt;
    }
    public String isNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getNationalityCnt() {
        return nationalityCnt;
    }
    public void setNationalityCnt(String nationalityCnt) {
        this.nationalityCnt = nationalityCnt;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWeixin() {
        return weixin;
    }
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }
    public String getMarriageStat() {
        return marriageStat;
    }
    public void setMarriageStat(String marriageStat) {
        this.marriageStat = marriageStat;
    }
    public String getMarriageCnt() {
        return marriageCnt;
    }
    public void setMarriageCnt(String marriageCnt) {
        this.marriageCnt = marriageCnt;
    }
    public String getDocIdType() {
        return docIdType;
    }
    public void setDocIdType(String docIdType) {
        this.docIdType = docIdType;
    }
    public String getDocIdCnt() {
        return docIdCnt;
    }
    public void setDocIdCnt(String docIdCnt) {
        this.docIdCnt = docIdCnt;
    }
    public String getDocId() {
        return docId;
    }
    public void setDocId(String docId) {
        this.docId = docId;
    }
    public int getChildCount() {
        return childCount;
    }
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getBackgd() {
        return backgd;
    }
    public void setBackgd(int backgd) {
        this.backgd = backgd;
    }
    public String getBackgdCnt() {
        return backgdCnt;
    }
    public void setBackgdCnt(String backgdCnt) {
        this.backgdCnt = backgdCnt;
    }
    public String getHighestSpeciality() {
        return highestSpeciality;
    }
    public void setHighestSpeciality(String highestSpeciality) {
        this.highestSpeciality = highestSpeciality;
    }
    public String getHighestSchool() {
        return highestSchool;
    }
    public void setHighestSchool(String highestSchool) {
        this.highestSchool = highestSchool;
    }
    public int getStudyAbroad() {
        return studyAbroad;
    }
    public void setStudyAbroad(int studyAbroad) {
        this.studyAbroad = studyAbroad;
    }
    public int getWorkAbroad() {
        return workAbroad;
    }
    public void setWorkAbroad(int workAbroad) {
        this.workAbroad = workAbroad;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public long getFstJobDate() {
        return fstJobDate;
    }
    public void setFstJobDate(long fstJobDate) {
        this.fstJobDate = fstJobDate;
    }
    public String getFstJobDateCnt() {
        return fstJobDateCnt;
    }
    public void setFstJobDateCnt(String fstJobDateCnt) {
        this.fstJobDateCnt = fstJobDateCnt;
    }
    public long getLstJobDate() {
        return lstJobDate;
    }
    public void setLstJobDate(long lstJobDate) {
        this.lstJobDate = lstJobDate;
    }
    public String getLstJobDateCnt() {
        return lstJobDateCnt;
    }
    public void setLstJobDateCnt(String lstJobDateCnt) {
        this.lstJobDateCnt = lstJobDateCnt;
    }
    public String getDrivingLic() {
        return drivingLic;
    }
    public void setDrivingLic(String drivingLic) {
        this.drivingLic = drivingLic;
    }
    public String getExtMessage() {
        return extMessage;
    }
    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }
    public String getExtUrl() {
        return extUrl;
    }
    public void setExtUrl(String extUrl) {
        this.extUrl = extUrl;
    }
    public int getIsEnglish() {
        return isEnglish;
    }
    public void setIsEnglish(int isEnglish) {
        this.isEnglish = isEnglish;
    }
    public int getManageSkill() {
        return manageSkill;
    }
    public void setManageSkill(int manageSkill) {
        this.manageSkill = manageSkill;
    }
    public String getLogoPath() {
        return logoPath;
    }
    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    public List<Appendix> getAppendix() {
        return appendix;
    }
    public void setAppendix(List<Appendix> appendix) {
        this.appendix = appendix;
    }
    public List<Certification> getCertification() {
        return certification;
    }
    public void setCertification(List<Certification> certification) {
        this.certification = certification;
    }
    public String getCertificationCnt() {
        return certificationCnt;
    }
    public void setCertificationCnt(String certificationCnt) {
        this.certificationCnt = certificationCnt;
    }
    public List<SelfAssessment> getSelfAssessment() {
        return selfAssessment;
    }
    public void setSelfAssessment(List<SelfAssessment> selfAssessment) {
        this.selfAssessment = selfAssessment;
    }
    public List<Education> getEducation() {
        return education;
    }
    public void setEducation(List<Education> education) {
        this.education = education;
    }
    public String getEducation_cnt() {
        return education_cnt;
    }
    public void setEducation_cnt(String education_cnt) {
        this.education_cnt = education_cnt;
    }
    public List<Language> getLanguage() {
        return language;
    }
    public void setLanguage(List<Language> language) {
        this.language = language;
    }
    public String getLanguage_cnt() {
        return language_cnt;
    }
    public void setLanguage_cnt(String language_cnt) {
        this.language_cnt = language_cnt;
    }
    public List<Project> getProjects() {
        return projects;
    }
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    public List<ProjectsCnt> getProjectsCnt() {
        return projectsCnt;
    }
    public void setProjectsCnt(List<ProjectsCnt> projectsCnt) {
        this.projectsCnt = projectsCnt;
    }
    public List<Work> getWork() {
        return work;
    }
    public void setWork(List<Work> work) {
        this.work = work;
    }
    public List<WorkCnt> getWorkCnt() {
        return workCnt;
    }
    public void setWorkCnt(List<WorkCnt> workCnt) {
        this.workCnt = workCnt;
    }
    public List<Skill> getSkills() {
        return skills;
    }
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    public String getSkillsCnt() {
        return skillsCnt;
    }
    public void setSkillsCnt(String skillsCnt) {
        this.skillsCnt = skillsCnt;
    }
    public List<Training> getTraining() {
        return training;
    }
    public void setTraining(List<Training> training) {
        this.training = training;
    }
    public String getTrainingCnt() {
        return trainingCnt;
    }
    public void setTrainingCnt(String trainingCnt) {
        this.trainingCnt = trainingCnt;
    }
    public String getHandsNnExp() {
        return handsNnExp;
    }
    public void setHandsNnExp(String handsNnExp) {
        this.handsNnExp = handsNnExp;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    
    public String getSelfEval(){
        if(selfAssessment!=null){
            StringBuilder sb = new StringBuilder();
            for(SelfAssessment sa:selfAssessment){
                sb.append(sa.getContent());
            }
            return sb.toString();
        }
        return "";
    }

}
