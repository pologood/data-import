package com.bj58.chr.data.handler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import com.bj58.chr.data.model.kv.KVName;
import com.bj58.chr.data.service.IKVMapService;

/**
 * 简历内容处理方法
 * 
 * @author sunlingao@58.com
 * @date 2016年4月7日
 */
public abstract class CVContentHandler implements ICVContentHandler {

    final Logger logger = Logger.getLogger(CVContentHandler.class);

    @Resource
    protected IKVMapService kVMapService;

    public abstract Object initBaseInfo(Object target);

    public abstract void buildList(Object target, Object bo);

    @SuppressWarnings("unchecked")
    public <T, S> S build(T target) {
        S bo = (S) initBaseInfo(target);
        buildList(target, bo);
        return bo;
    }
    
    /**
     * 通过k-v映射规则set value
     * @author:sunlingao@58.com
     * @date:2016年4月21日
     * @param sBo 
     * @param discs set字段
     * @param source get字段
     * @param kvFields 映射字段
     * @param groupId 映射组id
     * void
     */
    public <T> T setProperty(T sBo, String[] discs, String source, String kvFields[],
            int groupId) {
        try {
            int id = NumberUtils.toInt(source, 0);
            if(id == 0) return sBo;
            KVName kv = kVMapService.getIdValue(groupId, id);
            if (kv != null) {
                for (int i = 0; i < discs.length; i++) {
                    Field field = kv.getClass().getDeclaredField(kvFields[i]);
                    field.setAccessible(true);
                    BeanUtils.setProperty(sBo, discs[i], field.get(kv));
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            logger.error("Builder.setProperty", e);
        }
        return sBo;
    }
    
    /**
     * 
     * @author:sunlingao@58.com
     * @date:2016年4月21日
     * @param obj
     * @param disc
     * @param value
     * void
     */
    public <T> T setProperty(T obj,String disc,Object value){
        try {
            BeanUtils.setProperty(obj, disc, value);
        } catch (IllegalAccessException |InvocationTargetException e) {
            logger.error("Builder.setProperty", e);
        }
        return obj;
    }

    public final <T, S> Builder<T, S> builder(T target, Class<S> source) {
        try {
            return new Builder<T, S>(target, source.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("CVContentHandler.Builder", e);
        }
        return null;
    }

    public class Builder<T, S> {

        private T tModel;
        private S sBo;

        public Builder(T tModel, S sBo) {
            this.tModel = tModel;
            this.sBo = sBo;
        }

        public Builder<T, S> setProperty(String disc, String source) {
            try {
                BeanUtils.setProperty(sBo, disc, BeanUtils.getProperty(tModel, source));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                logger.error("Builder.setProperty", e);
            }
            return this;
        }

        public Builder<T, S> setProperty(String[] discs, String source, int groupId) {
            return setProperty(discs, source, new String[] {"id", "name"}, groupId);
        }

        public Builder<T, S> setProperty(String[] discs, String source, String kvFields[],
                int groupId) {
            try {
                int value = NumberUtils.toInt(BeanUtils.getProperty(tModel, source), 0);
                if (value == 0) {
                    return this;
                }
                KVName kv = kVMapService.getIdValue(groupId, value);
                if (kv != null) {
                    for (int i = 0; i < discs.length; i++) {
                        Field field = kv.getClass().getDeclaredField(kvFields[i]);
                        field.setAccessible(true);
                        BeanUtils.setProperty(sBo, discs[i], field.get(kv));
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                    | NoSuchFieldException e) {
                e.printStackTrace();
                logger.error("Builder.setProperty", e);
            }
            return this;
        }

        public S getBo() {
            return sBo;
        }
    }
}
