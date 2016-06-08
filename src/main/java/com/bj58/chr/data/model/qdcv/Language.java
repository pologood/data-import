package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:35:17
 * @see
 * @since
 */
public class Language {

	private String language;// : "语言ID", // ======== 需要字典语言 ========
	@JsonProperty("lang_cnt")
	private String langCnt;// : "语言原文",
	private int verbal;// : "听说能力", //不限:1 一般:2 良好:3 熟练:4 精通:5
	@JsonProperty("verbal_cnt")
	private String verbalCnt;// : "听说能力原文",
	private String literacy;// : ", //不限:1 一般:2 良好:3 熟练:4 精通:5
	@JsonProperty("literacy_cnt")
	private String literacyCnt;// : "读写能力原读写能力"文",
	@JsonProperty("all_level")
	private String allLevel;// : "听说读写能力",
	@JsonProperty("lang_level")
	private String langLevel;// : "语言等级" // new 值？
	
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getLangCnt() {
        return langCnt;
    }
    public void setLangCnt(String langCnt) {
        this.langCnt = langCnt;
    }
    public long getVerbal() {
        return verbal;
    }
    public void setVerbal(int verbal) {
        this.verbal = verbal;
    }
    public String getVerbalCnt() {
        return verbalCnt;
    }
    public void setVerbalCnt(String verbalCnt) {
        this.verbalCnt = verbalCnt;
    }
    public String getLiteracy() {
        return literacy;
    }
    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }
    public String getLiteracyCnt() {
        return literacyCnt;
    }
    public void setLiteracyCnt(String literacyCnt) {
        this.literacyCnt = literacyCnt;
    }
    public String getAllLevel() {
        return allLevel;
    }
    public void setAllLevel(String allLevel) {
        this.allLevel = allLevel;
    }
    public String getLangLevel() {
        return langLevel;
    }
    public void setLangLevel(String langLevel) {
        this.langLevel = langLevel;
    }
	
}
