package com.bj58.chr.data.model.yccv;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 技能实体
 * @author sunlingao@58.com
 * @date 2016年3月10日
 */
public class ITSkillsBo {


	@NotNull
	private String id;
	@Pattern(regexp="\\d+",flags=Pattern.Flag.CASE_INSENSITIVE)
	private String techId;
	
	private String techName;
	
	private String bigId;
	
	private String bigName;
	
	private String childId;
	
	private String childName;
	
	private String levelId;
	
	private String levelName;
	
	private String isDel;
	
	private Long delTime;
	
	private Long addTime;
	
	private Long useTimeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public String getBigId() {
        return bigId;
    }

    public void setBigId(String bigId) {
        this.bigId = bigId;
    }

    public String getBigName() {
        return bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Long getDelTime() {
        return delTime;
    }

    public void setDelTime(Long delTime) {
        this.delTime = delTime;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getUseTimeId() {
        return useTimeId;
    }

    public void setUseTimeId(Long useTimeId) {
        this.useTimeId = useTimeId;
    }

	
	
	
}
