package com.bj58.chr.data.esb.message;

import com.bj58.chr.common.web.IntPersistable;
import com.bj58.chr.common.web.StringPersistable;

/**
 * 消息实体
 * @author sunlingao@58.com
 * @date 2016年4月8日
 */
public class Message extends StringPersistable{

    /**
     * date:2016年4月8日
     * field:serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    protected String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
