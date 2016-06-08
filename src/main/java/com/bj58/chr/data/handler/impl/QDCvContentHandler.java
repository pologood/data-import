package com.bj58.chr.data.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.bj58.chr.data.handler.CVContentHandler;
import com.bj58.chr.data.model.kv.KVName;
import com.bj58.chr.data.model.qdcv.CVModel;
import com.bj58.chr.data.model.qdcv.Certification;
import com.bj58.chr.data.model.qdcv.DeliverInfo;
import com.bj58.chr.data.model.qdcv.Education;
import com.bj58.chr.data.model.qdcv.JobIntension;
import com.bj58.chr.data.model.qdcv.Language;
import com.bj58.chr.data.model.qdcv.Project;
import com.bj58.chr.data.model.qdcv.ResumeInfo;
import com.bj58.chr.data.model.qdcv.Training;
import com.bj58.chr.data.model.qdcv.Work;
import com.bj58.chr.data.model.yccv.CertsBo;
import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.model.yccv.EducationsBo;
import com.bj58.chr.data.model.yccv.ExperiencesBo;
import com.bj58.chr.data.model.yccv.IndustryBo;
import com.bj58.chr.data.model.yccv.JobTypeForExper;
import com.bj58.chr.data.model.yccv.LangSkillsBo;
import com.bj58.chr.data.model.yccv.ProjectsBo;
import com.bj58.chr.data.model.yccv.RegionalismBo;
import com.bj58.chr.data.model.yccv.TrainingsBo;
import com.bj58.chr.data.utils.Constant;
import com.bj58.chr.data.utils.QDStringUtils;
import com.google.common.collect.Lists;

/**
 * 
 * 
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午5:48:08
 * @see
 * @desc 时间范围{start-end} 结束时间为0都初始化"至今"
 * @since
 */
@Service("qdCvContentHandler")
public class QDCvContentHandler extends CVContentHandler {

    private final Logger logger = Logger.getLogger(QDCvContentHandler.class);
    
    /**
     * @author:sunlingao@58.com
     * @desc: 初始化基础信息
     * @date:2016年4月6日
     * @param cvModel
     * @return CvInfoBo
     */
    @Override
    public CvInfoBo initBaseInfo(Object obj) {
        CVModel cvModel = (CVModel) obj;
        Builder<ResumeInfo, CvInfoBo> builder = builder(cvModel.getResumeInfo(), CvInfoBo.class);
        builder.setProperty("nationality", "nationalityCnt").setProperty("cnName", "name")
        .setProperty("cvName", "rname").setProperty("birthday", "birthday")
        .setProperty(new String[]{"degreeId","degreeName"}, "backgd", 9)
                .setProperty("workTime", "fstJobDate")
                .setProperty("selfEval", "selfEval").setProperty(new String[]{"gender"}, "gender",new String[]{"name"}, 8)
                .setProperty(new String[]{"marry"}, "marriageStat",new String[]{"name"},12).setProperty("isEnCV", "isEnglish");
        builder.getBo().setLiving(buildRegion(cvModel.getResumeInfo().getCurCityId()));
        setProperty(builder.getBo(), "domicile", buildRegion(cvModel.getResumeInfo().getHukou()));
        setProperty(builder.getBo(), "cvName", "我的简历");
        setProperty(builder.getBo(), "classify", 0L);
        setProperty(builder.getBo(), "isDel", 0);
        setProperty(builder.getBo(), "src", 10L);
        buildExpectation(cvModel,builder.getBo());
        return builder.getBo();
    }
    
    /**
     * 构建居住城市信息
     * @author:sunlingao@58.com
     * @date:2016年4月19日
     * @param curCityId 当前城市id
     * @param bo
     * void
     */
    private RegionalismBo buildRegion(String curCityId){
        int cityId = NumberUtils.toInt(curCityId, 0);
        if(cityId == 0) return null;
        KVName kv = kVMapService.getIdValue(11, cityId);
        if(kv!=null){
            String ids = kv.getId();
            String names = kv.getName();
            RegionalismBo region = new RegionalismBo();
            if(ids.split(Constant.SPLIT_SYMBOL).length == names.split(Constant.SPLIT_SYMBOL).length){
                String idarr[] = ids.split(Constant.SPLIT_SYMBOL);
                String namearr[] = names.split(Constant.SPLIT_SYMBOL);
                switch (idarr.length) {
                    case 3:
                        region.setDistId(idarr[0]);
                        region.setDistName(namearr[0]);
                    case 2:
                        region.setCityId(idarr[idarr.length-2]);
                        region.setCityName(namearr[idarr.length-2]);
                    case 1:
                        region.setProvId(idarr[idarr.length-1]);
                        region.setProvName(namearr[idarr.length-1]);
                }
            }
            return region;
        }
        return null;
    }
    
    /**
     * 构建期望工作类别
     * @author:sunlingao@58.com
     * @date:2016年4月19日
     * @param jobid qd类别id
     * @return
     * JobTypeForExper
     */
    private JobTypeForExper buildJobType(String jobid){
        int id = NumberUtils.toInt(jobid, 0);
        if(id == 0) return null;
        KVName kv = kVMapService.getIdValue(15, id);
        if(kv!=null){
            JobTypeForExper exper = new JobTypeForExper();
            String idarr[] = kv.getId().split(Constant.SPLIT_SYMBOL);
            String namearr[] = kv.getName().split(Constant.SPLIT_SYMBOL);
            if(idarr.length == namearr.length){
                switch (idarr.length) {
                    case 3:
                        exper.setJobNameId(idarr[0]);
                        exper.setJobName(namearr[0]);
                    case 2:
                        exper.setCategoryId(idarr[idarr.length-2]);
                        exper.setCategoryName(namearr[idarr.length-2]);
                    case 1:
                        exper.setBigId(idarr[idarr.length-1]);
                        exper.setBigName(namearr[idarr.length-1]);
                }
            }
            return exper;
        }
        return null;
    }
    
    /**
     * 构建求职意向
     * @author:sunlingao@58.com
     * @date:2016年4月19日
     * @param model
     * void
     */
    private void buildExpectation(CVModel model,CvInfoBo bo){
        if(model.getDeliverInfo()!=null && model.getDeliverInfo().get(0).getJobIntension()!=null){
            DeliverInfo info = model.getDeliverInfo().get(0);
            JobIntension job = info.getJobIntension();
            /**期望城市*/
            if(ArrayUtils.isNotEmpty(job.getCity())){
                List<RegionalismBo> expLocal = Lists.newArrayList();
                for(int city:job.getCity()){
                    RegionalismBo region = buildRegion(String.valueOf(city));
                    if(region != null) expLocal.add(region); 
                }
                bo.setExpLocation(expLocal);
            }
            /**期望行业*/
            if(ArrayUtils.isNotEmpty(job.getTrade())){
                List<IndustryBo> expIndustry = Lists.newArrayList();
                for(String trade:job.getTrade()){
                    IndustryBo industry = buildIndustry(trade);
                    if(industry!=null) expIndustry.add(industry); 
                }
                bo.setExpIndustry(expIndustry);
            }
            /**期望职位类别*/
            if(ArrayUtils.isNotEmpty(job.getJob())){
                List<JobTypeForExper> expJobs = Lists.newArrayList();
                for(String jobid:job.getJob()){
                    JobTypeForExper jobType = buildJobType(jobid);
                    if(jobType!=null) expJobs.add(jobType);
                }
                bo.setExpJobs(expJobs);
            }
            bo.setExpSalary(NumberUtils.toLong(String.valueOf(job.getHopeSalaryTo()), 0L));
            setProperty(bo, new String[]{"expComTypeId"}, job.getCompanyType(), new String[]{"id"}, 14);
            setProperty(bo, new String[]{"expJobTypeId"}, job.getJobNature(), new String[]{"id"}, 16);
            /**修改时间*/
            bo.setAddTime(model.getCreateTime() <= 0?info.getAppointTime():model.getCreateTime());
            bo.setLModTime(QDStringUtils.getCvTime(System.currentTimeMillis()));
            bo.setLRefTime(getMaxTime(info.getUpdateTime(), info.getDispTime(),info.getRefreshTime(),info.getUpdateTime(),info.getAppointTime()));
        }
    }
    
    /**
     * 构建行业数据
     * @author:sunlingao@58.com
     * @date:2016年4月19日
     * @param trade
     * @return
     * IndustryBo
     */
    private IndustryBo buildIndustry(String trade){
        int id = NumberUtils.toInt(trade, 0);
        if(id == 0) return null;
        KVName kv = kVMapService.getIdValue(17, id);
        if(kv!=null){
            IndustryBo bo = new IndustryBo();
            String[] idarr = kv.getId().split(Constant.SPLIT_SYMBOL);
            String[] namearr = kv.getName().split(Constant.SPLIT_SYMBOL);
            if(idarr.length == namearr.length){
                switch (idarr.length) {
                    case 2:
                       bo.setInduId(idarr[0]);
                       bo.setInduName(namearr[0]);
                    case 1:
                       bo.setBigId(idarr[1]);
                       bo.setBigName(namearr[1]);
                }
            }
            return bo;
        }
        return null;
    }
    
    
    @Override
    public void buildList(Object target, Object obj) {
        CVModel cvModel = (CVModel) target;
        CvInfoBo bo = (CvInfoBo) obj;
        // 构建教育经历
        buildEducationList(bo, cvModel.getResumeInfo().getEducation());
        // 构建项目经历
        buildProjectList(bo, cvModel.getResumeInfo().getProjects());
        // 构建工作经历
        buildExperienceList(bo, cvModel.getResumeInfo().getWork());
        // 构建语言技能
        buildLanguageList(bo, cvModel.getResumeInfo().getLanguage());
        // 构建证书技能
        buildCertList(bo, cvModel.getResumeInfo().getCertification());
        // 构建培训经历
        buildTrainList(bo, cvModel.getResumeInfo().getTraining());
    }


    /**
     * @author:sunlingao@58.com
     * @desc:构建教育经历
     * @date:2016年4月6日
     * @param bo
     * @param list void
     */
    private void buildEducationList(CvInfoBo bo, List<Education> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setEducation(new ArrayList<EducationsBo>(list.size()));
            for (Education edu : list) {
                bo.getEducation().add(convertEducation(edu));
            }
        }
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc:转换教育经历
     * @date:2016年4月6日
     * @param edu
     * @return EducationsBo
     */
    private EducationsBo convertEducation(Education edu) {
        Builder<Education, EducationsBo> builder = builder(edu, EducationsBo.class);
        builder.setProperty("start", "stime").setProperty("end", "etime")
                .setProperty(new String[]{"degreeId","degreeName"}, "backgd", 9)
                .setProperty("description", "description").setProperty("college", "school")
                .setProperty("major", "speciality");
                return setProperty(builder.getBo(), "id", ObjectId.get().toString());
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc: 构建项目经历
     * @date:2016年4月6日
     * @param bo
     * @param list void
     */
    private void buildProjectList(CvInfoBo bo, List<Project> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setProjects(new ArrayList<ProjectsBo>(list.size()));
            for (Project pro : list) {
                bo.getProjects().add(convertProject(pro));
            }
        }
    }

    /**
     * @author:sunlingao@58.com
     * @desc: 转换项目经历
     * @date:2016年4月6日
     * @param pro
     * @return ProjectsBo
     */
    private ProjectsBo convertProject(Project pro) {
        Builder<Project, ProjectsBo> builder = builder(pro, ProjectsBo.class);
        if(pro.getStime()>0){
            builder.setProperty("start", "stime");
        }
        if(pro.getEtime()>0){
            builder.setProperty("end", "etime");
        }
        builder.setProperty("name", "projectName").setProperty("projDesc", "description")
                .setProperty("resp", "duty");
        setProperty(builder.getBo(), "id", ObjectId.get().toString());
        return builder.getBo();
    }

    /**
     * @author:sunlingao@58.com
     * @desc:构建工作 经验
     * @date:2016年4月7日
     * @param bo
     * @param list void
     */
    private void buildExperienceList(CvInfoBo bo, List<Work> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setExperience(new ArrayList<ExperiencesBo>(list.size()));
            for (Work work : list) {
                bo.getExperience().add(convertWork(work));
            }
        }
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc:转换工作经历
     * @date:2016年4月7日
     * @return ExperiencesBo
     */
    private ExperiencesBo convertWork(Work work) {
        Builder<Work, ExperiencesBo> builder = builder(work, ExperiencesBo.class);
        builder.setProperty("start", "stime").setProperty("end", "etime")
                .setProperty("comName", "company").setProperty("comSize", "companySizeCnt")
                .setProperty("department", "department").setProperty("inputJobName", "job")
                .setProperty("workDesc", "jobDescription").setProperty(new String[]{"comTypeId","comType"}, "companyType",new String[]{"id","name"}, 14);
        List<IndustryBo> list = new ArrayList<IndustryBo>(1);
        list.add(buildIndustry(work.getTrade()));
        setProperty(builder.getBo(), "industry", list);
        setProperty(builder.getBo(), "id", ObjectId.get().toString());
        /**解析薪水*/
        String salary = work.getSalaryTo();
        if(!StringUtils.isEmpty(salary)){
        builder.getBo().setSalaryId(QDStringUtils.getSalary(salary));
        builder.getBo().setSalary(salary);
        }
        if(StringUtils.isEmpty(builder.getBo().getWorkDesc())){
            builder.setProperty("workDesc", "duty");
        }
        return builder.getBo();
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc: 构建语言技能
     * @date:2016年4月7日
     * @param bo
     * @param list void
     */
    private void buildLanguageList(CvInfoBo bo, List<Language> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setLangSkills(new ArrayList<LangSkillsBo>(list.size()));
            for (Language lan : list) {
                bo.getLangSkills().add(convertLanguage(lan));
            }
        }
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc:转换语言技能
     * @date:2016年4月7日
     * @param lan
     * @return LangSkillsBo
     */
    private LangSkillsBo convertLanguage(Language lan) {
        Builder<Language, LangSkillsBo> builder = builder(lan, LangSkillsBo.class);
        int id = NumberUtils.toInt(lan.getLanguage(), 0);
        if(id == 0) return builder.getBo();
        KVName kv = kVMapService.getIdValue(13, id);
        LangSkillsBo bo = builder.getBo();
        String[] idarr = kv.getId().split(Constant.SPLIT_SYMBOL);
        String[] namearr = kv.getName().split(Constant.SPLIT_SYMBOL);
        setProperty(bo, "typeId", idarr[1]);
        setProperty(bo, "typeName", namearr.length==1?namearr[0]:namearr[1]);
        setProperty(bo, "langId", idarr[0]);
        setProperty(bo, "langValue", namearr[0]);
        KVName skilled = kVMapService.getIdValue(19, (int)lan.getVerbal());
        if(skilled != null){
        setProperty(bo, "level", skilled.getName());
        setProperty(bo, "levelId", skilled.getId());
        }
        return setProperty(bo, "id", ObjectId.get().toString());
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc:构建证书
     * @date:2016年4月7日
     * @param bo
     * @param list void
     */
    private void buildCertList(CvInfoBo bo, List<Certification> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setCerts(new ArrayList<CertsBo>(list.size()));
            for (Certification cert : list) {
                bo.getCerts().add(convertCertification(cert));
            }
        }
    }

    /**
     * 
     * @author:sunlingao@58.com
     * @desc:转换证书
     * @date:2016年4月7日
     * @param cert
     * @return CertsBo
     */
    private CertsBo convertCertification(Certification cert) {
        Builder<Certification, CertsBo> builder = builder(cert, CertsBo.class);
        Date date = QDStringUtils.formatDateForStr(cert.getIssued());
        if(date != null){
        builder.getBo().setTime(QDStringUtils.getCvTime(date.getTime()));
        }
        builder.setProperty("certName", "cerName");
        return setProperty(builder.getBo(), "id", ObjectId.get().toString());
    }
    
    /**
     * 构建培训经历列表
     * @author:sunlingao@58.com
     * @date:2016年5月5日
     * @param bo
     * @param list
     * void
     */
    public void buildTrainList(CvInfoBo bo,List<Training> list){
        if (CollectionUtils.isNotEmpty(list)) {
            bo.setTraining(new ArrayList<TrainingsBo>(list.size()));
            for (Training train : list) {
                bo.getTraining().add(convertTraining(train));
            }
        }
    }
    
    private TrainingsBo convertTraining(Training train){
        Builder<Training, TrainingsBo> builder = builder(train, TrainingsBo.class);
        builder.setProperty("start", "stime").setProperty("end", "etime");
        builder.setProperty("school", "agency").setProperty("desc", "description")
        .setProperty("course", "course");
        return setProperty(builder.getBo(), "id", ObjectId.get().toString());
    }
    
    private long getMaxTime(long defaultTime,long ...ls){
        for(long time : ls){
            if( defaultTime < time ) defaultTime = time;
        }
        return defaultTime;
    }
}
