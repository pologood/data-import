package com.bj58.chr.data.xls;

import org.hibernate.validator.constraints.NotEmpty;

import com.bj58.chr.common.poi.annotation.ElColumn;
import com.bj58.chr.common.poi.annotation.ElEntity;

/**
 * 学历xls实体
 * @author sunlingao@58.com
 * @date 2016年4月18日
 */
@ElEntity
public class Degrees {
    
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达", index = 0)
    private String qdId;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达", index = 1)
    private String qdName;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达", index = 2)
    private String ycId;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达", index = 3)
    private String ycName;
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
    public String getYcId() {
        return ycId;
    }
    public void setYcId(String ycId) {
        this.ycId = ycId;
    }
    public String getYcName() {
        return ycName;
    }
    public void setYcName(String ycName) {
        this.ycName = ycName;
    }
    
}
