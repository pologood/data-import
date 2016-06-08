package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:50:37
 * @see
 * @since
 */
public class Skill {

	private String skill;// : "技能名称",
	private String level;// : "技能水平", //不限:1 一般:2 良好:3 熟练:4 精通:5
	@JsonProperty("level_cnt")
	private String levelCnt;// : "技能水平原文",
	@JsonProperty("how_long_month")
	private String howLongMonth;// : "使用时间原文"
	
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getLevelCnt() {
        return levelCnt;
    }
    public void setLevelCnt(String levelCnt) {
        this.levelCnt = levelCnt;
    }
    public String getHowLongMonth() {
        return howLongMonth;
    }
    public void setHowLongMonth(String howLongMonth) {
        this.howLongMonth = howLongMonth;
    }
	
}
