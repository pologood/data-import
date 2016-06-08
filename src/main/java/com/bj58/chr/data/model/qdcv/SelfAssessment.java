package com.bj58.chr.data.model.qdcv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 上午12:32:48
 * @see
 * @since
 */
public class SelfAssessment {

	@JsonProperty("content_type")
	private String contentType;// : "自我评价类型",
	private String content;// : "自我评价内容"
	
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
	
}
