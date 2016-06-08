package com.bj58.chr.data.interceptor.core;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

public interface Action {

	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			BoundSql boundSql, ChrExecutor qmpExecutor, Shard shardInterceptor) throws SQLException;

	public int update(MappedStatement ms, StatementHandler handler, ChrExecutor qmpExecutor,
			Shard shardInterceptor) throws SQLException;

}
