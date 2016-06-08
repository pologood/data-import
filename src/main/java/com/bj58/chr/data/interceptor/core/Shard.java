package com.bj58.chr.data.interceptor.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切片拦截器
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Shard {

	ShardType shardType() default ShardType.HASH;// 切分类型

	String args() default "";// 处理的参数

	String join() default "_";// 连接符

	int part() default 0;

}
