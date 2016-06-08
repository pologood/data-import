package com.bj58.chr.data.interceptor.impl;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.bj58.chr.data.interceptor.core.BaseAction;
import com.bj58.chr.data.interceptor.core.ChrExecutor;
import com.bj58.chr.data.interceptor.core.Shard;

import java.sql.SQLException;
import java.util.List;

public class DefaultAction extends BaseAction {

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			BoundSql boundSql, ChrExecutor chrExecutor, Shard shardInterceptor) throws SQLException {
		return query(ms, parameter, rowBounds, resultHandler, boundSql, chrExecutor);
	}

	@Override
	public int update(MappedStatement ms, StatementHandler handler, ChrExecutor chrExecutor,
			Shard shardInterceptor) throws SQLException {
		return update(ms, handler, chrExecutor);
	}

}
