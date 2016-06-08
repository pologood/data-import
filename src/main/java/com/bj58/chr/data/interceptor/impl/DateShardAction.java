package com.bj58.chr.data.interceptor.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.bj58.chr.data.interceptor.core.BaseAction;
import com.bj58.chr.data.interceptor.core.ChrExecutor;
import com.bj58.chr.data.interceptor.core.Shard;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午11:09:51
 * @see
 * @since
 */
public class DateShardAction extends BaseAction {

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			BoundSql boundSql, ChrExecutor qmpExecutor, Shard shardInterceptor) throws SQLException {
		return null;
	}

	@Override
	public int update(MappedStatement ms, StatementHandler handler, ChrExecutor qmpExecutor,
			Shard shardInterceptor) throws SQLException {
		return 0;
	}

}
