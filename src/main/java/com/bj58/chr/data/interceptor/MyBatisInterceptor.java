package com.bj58.chr.data.interceptor;

import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.chr.data.interceptor.core.ChrExecutor;

import java.util.Properties;

/**
 * 负责拦截
 * 
 */

@Intercepts({
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }),
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MyBatisInterceptor implements Interceptor {

	private static final Logger LOG = LoggerFactory.getLogger(MyBatisInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (!(invocation.getTarget() instanceof CachingExecutor)) {
			LOG.error("invocation.getTarget() is not CachingExecutor mybatis 拦截器代理类型错误 ");
			throw new RuntimeException();
		}
		CachingExecutor cachingExecutor = (CachingExecutor) invocation.getTarget();
		ChrExecutor.newExecutor(cachingExecutor);
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
