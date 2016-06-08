package com.bj58.chr.data.handler;

import com.bj58.chr.data.model.kv.KVGroup;

/**
 * 字典导入处理器
 * @author sunlingao@58.com
 * @date 2016年4月18日
 * @param <T>
 */
public interface ImportHandler<T> {
    
    public void execute(KVGroup group, T t);
    
}
