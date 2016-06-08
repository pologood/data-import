package com.bj58.chr.data.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.bj58.chr.common.web.IntPersistable;

/**
 * 合作id黑名单
 * @author sunlingao@58.com
 * @date 2016年5月16日
 */
@Alias("BlackList")
public class BlackList extends IntPersistable{

    /**
     * date:2016年5月16日
     * field:serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private String coid;
    
    private String cvid;
    
    private String mobile;
    
    private Date addTime;
    
    private Date updateTime;

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public String getCvid() {
        return cvid;
    }

    public void setCvid(String cvid) {
        this.cvid = cvid;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
