package com.bj58.chr.data.xls;

import org.hibernate.validator.constraints.NotEmpty;

import com.bj58.chr.common.poi.annotation.ElColumn;
import com.bj58.chr.common.poi.annotation.ElEntity;

/**
 * 语言映射
 * @author sunlingao@58.com
 * @date 2016年4月19日
 */
@ElEntity
public class Language {

    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达id", index = 0)
    private String qdId;
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达名称", index = 1)
    private String qdName;
    @ElColumn(name = "英才语种id", index = 2)
    private String ycRootId;
    @ElColumn(name = "英才语种列表id", index = 3)
    private String ycListId;
    @ElColumn(name = "英才语种名称", index = 4)
    private String ycRootName;
    @ElColumn(name = "英才语种列表名称", index = 5)
    private String ycListName;
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
    public String getYcRootId() {
        return ycRootId;
    }
    public void setYcRootId(String ycRootId) {
        this.ycRootId = ycRootId;
    }
    public String getYcListId() {
        return ycListId;
    }
    public void setYcListId(String ycListId) {
        this.ycListId = ycListId;
    }
    public String getYcRootName() {
        return ycRootName;
    }
    public void setYcRootName(String ycRootName) {
        this.ycRootName = ycRootName;
    }
    public String getYcListName() {
        return ycListName;
    }
    public void setYcListName(String ycListName) {
        this.ycListName = ycListName;
    }
    
}
