package com.bj58.chr.data.service;

import com.bj58.chr.data.model.kv.KVName;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date  2016年4月2日 下午10:38:21
 * @see 
 * @since  
 */
public interface IKVMapService {
    
    /**
     * 
     * @author:sunlingao@58.com
     * @desc:
     * @date:2016年4月6日
     * @param groupId
     * @param qdId
     * @return
     * KVName
     */
    KVName getIdValue(int groupId, int qdId);
    
    /**
     * 
     * @author:sunlingao@58.com
     * @desc:
     * @date:2016年4月6日
     * @param groupId
     * @param qdName
     * @return
     * KVName
     */
    KVName getNameValue(int groupId, int qdName);
}
