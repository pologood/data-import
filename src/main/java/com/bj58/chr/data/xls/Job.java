package com.bj58.chr.data.xls;

import org.hibernate.validator.constraints.NotEmpty;

import com.bj58.chr.common.poi.annotation.ElColumn;
import com.bj58.chr.common.poi.annotation.ElEntity;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月14日 下午12:01:09
 * @see
 * @since
 */

@ElEntity
public class Job {
    
    @NotEmpty(message = "不能为空")
    @ElColumn(name = "巧达ID", index = 0)
    private String qdId;
	@NotEmpty(message = "不能为空")
	@ElColumn(name = "巧达", index = 1)
	private String qdName;
	@ElColumn(name = "英才职位3级类别id", index = 2)
	private String ycThreeId;
	@ElColumn(name = "英才职位2级类别id", index = 3)
	private String ycSecondId;
	@ElColumn(name = "英才职位1级类别id", index = 4)
	private String ycFristId;
	@ElColumn(name = "英才职位3级类别名称", index = 5)
	private String ycThreeName;
	@ElColumn(name = "英才职位2级类别名称", index = 6)
	private String ycSecondName;
	@ElColumn(name = "英才职位1级类别名称", index = 7)
	private String ycFirstName;
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
    public String getYcThreeId() {
        return ycThreeId;
    }
    public void setYcThreeId(String ycThreeId) {
        this.ycThreeId = ycThreeId;
    }
    public String getYcSecondId() {
        return ycSecondId;
    }
    public void setYcSecondId(String ycSecondId) {
        this.ycSecondId = ycSecondId;
    }
    public String getYcFristId() {
        return ycFristId;
    }
    public void setYcFristId(String ycFristId) {
        this.ycFristId = ycFristId;
    }
    public String getYcThreeName() {
        return ycThreeName;
    }
    public void setYcThreeName(String ycThreeName) {
        this.ycThreeName = ycThreeName;
    }
    public String getYcSecondName() {
        return ycSecondName;
    }
    public void setYcSecondName(String ycSecondName) {
        this.ycSecondName = ycSecondName;
    }
    public String getYcFirstName() {
        return ycFirstName;
    }
    public void setYcFirstName(String ycFirstName) {
        this.ycFirstName = ycFirstName;
    }
	
}
