package com.bj58.chr.data.xls;

import org.hibernate.validator.constraints.NotEmpty;

import com.bj58.chr.common.poi.annotation.ElColumn;
import com.bj58.chr.common.poi.annotation.ElEntity;

/**
 * 地域映射信息
 * @author sunlingao@58.com
 * @date 2016年4月18日
 */
@ElEntity
public class Area {
    
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达id", index = 0)
    private String qdId;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达名称", index = 1)
    private String qdName;
    @ElColumn(name = "英才区级id", index = 2)
    private String ycDistrictId;
    @ElColumn(name = "英才市级id", index = 3)
    private String ycCityId;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "英才省级id", index = 4)
    private String ycProvId;
    @ElColumn(name = "英才区级名称", index = 5)
    private String ycDistrictName;
    @ElColumn(name = "英才市级名称", index = 6)
    private String ycCityName;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "英才省级名称", index = 7)
    private String ycProvName;
    public String getQdId() {
        return qdId;
    }
    public void setQdId(String qdId) {
        this.qdId = qdId;
    }
    public String getQdName() {
        return qdName;
    }
    public void setQdName(String qdName) {
        this.qdName = qdName;
    }
    public String getYcDistrictId() {
        return ycDistrictId;
    }
    public void setYcDistrictId(String ycDistrictId) {
        this.ycDistrictId = ycDistrictId;
    }
    public String getYcCityId() {
        return ycCityId;
    }
    public void setYcCityId(String ycCityId) {
        this.ycCityId = ycCityId;
    }
    public String getYcProvId() {
        return ycProvId;
    }
    public void setYcProvId(String ycProvId) {
        this.ycProvId = ycProvId;
    }
    public String getYcDistrictName() {
        return ycDistrictName;
    }
    public void setYcDistrictName(String ycDistrictName) {
        this.ycDistrictName = ycDistrictName;
    }
    public String getYcCityName() {
        return ycCityName;
    }
    public void setYcCityName(String ycCityName) {
        this.ycCityName = ycCityName;
    }
    public String getYcProvName() {
        return ycProvName;
    }
    public void setYcProvName(String ycProvName) {
        this.ycProvName = ycProvName;
    }
    
}
