package com.bj58.chr.data.esb.message;

import org.apache.commons.collections4.CollectionUtils;

import com.bj58.chr.data.model.yccv.CvInfoBo;
import com.bj58.chr.data.model.yccv.JobTypeForExper;
import com.bj58.chr.data.model.yccv.RegionalismBo;

/**
 * 召唤esb消息实体
 * @author sunlingao@58.com
 * @date 2016年4月20日
 */
public class CallBackMessage extends Message{
    
    /**
     * date:2016年4月20日
     * field:serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 简历id
     */
    private String cvid;
    
    /**
     * 昵称
     */
    private String uname;
    
    /**
     * 手机
     */
    private String mobile;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * c端用户id
     */
    private String uid;
    
    /**
     * 用户密码
     */
    private String pwd;
    
    /**
     * 期望职位类别
     */
    private JobTypeForExper job;
    
    /**
     * 期望地域
     */
    private RegionalismBo region;
    
    public CallBackMessage(){}
    
    public CallBackMessage(CvInfoBo bo,String pwd){
        this.cvid = bo.getId();
        this.uname = bo.getCnName();
        this.mobile = bo.getMobile();
        this.email = bo.getEmail();
        this.uid = bo.getUid();
        this.pwd = pwd;
        if(!CollectionUtils.isEmpty(bo.getExpJobs())){
            this.job = bo.getExpJobs().get(0);
        }
        if(!CollectionUtils.isEmpty(bo.getExpLocation())){
            this.region = bo.getExpLocation().get(0);
        }
    }

    public String getCvid() {
        return cvid;
    }

    public void setCvid(String cvid) {
        this.cvid = cvid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public JobTypeForExper getJob() {
        return job;
    }

    public void setJob(JobTypeForExper job) {
        this.job = job;
    }

    public RegionalismBo getRegion() {
        return region;
    }

    public void setRegion(RegionalismBo region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "CallBackMessage [cvid=" + cvid + ", uname=" + uname + ", mobile=" + mobile
                + ", email=" + email + ", uid=" + uid + ", pwd=" + pwd + ", job="
                + job + ", region=" + region + "]";
    }
    
}
