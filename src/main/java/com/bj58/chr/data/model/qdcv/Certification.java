package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:31:12
 * @see
 * @since
 */
public class Certification {
	@JsonProperty("cer_name")
	private String cerName;// : "证书名",
	private String issued;// : "颁发时间原文",
	private String description;// : "证书描述"
    public String getCerName() {
        return cerName;
    }
    public void setCerName(String cerName) {
        this.cerName = cerName;
    }
    public String getIssued() {
        return issued;
    }
    public void setIssued(String issued) {
        this.issued = issued;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	
}
