package com.bj58.chr.data.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.StringPersistable;

/**
 * 保存qd用户信息
 * @author sunlingao@58.com
 * @date 2016年4月9日
 */
@Alias("SeekerUser")
public class SeekerUser extends StringPersistable{
    
    /**
     * date:2016年4月9日
     * field:serialVersionUID
     */
    private static final long serialVersionUID = 4558455387252724798L;

    /**
     * mongo用户id
     */
    @NotNull
    private String uid;
    
    /**
     * 密码
     */
    @NotNull
    private String pwd;
    
    /**
     * 邮件
     */
    private String email;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 合作id
     */
    private String coId;

    /**
     * 扩展字段
     */
    private String ext;
    
    private Date createTime;
    
    private Date updateTime;
    
    public SeekerUser(){}
    
    public SeekerUser(String uid,String pwd,String coid){
        this.uid = uid;
        this.pwd = pwd;
        this.coId = coid;
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

    public String getEmail() {
        return email == null ? StringUtils.EMPTY : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile == null ? StringUtils.EMPTY : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExt() {
        return ext == null ? StringUtils.EMPTY : ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

}
