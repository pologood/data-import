package com.bj58.chr.data.interceptor.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.chr.data.interceptor.core.BaseAction;
import com.bj58.chr.data.interceptor.core.ChrExecutor;
import com.bj58.chr.data.interceptor.core.Shard;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月3日 下午9:38:48
 * @see
 * @since
 */
public class HashShardAction extends BaseAction {

	private final static Logger LOG = LoggerFactory.getLogger(HashShardAction.class);

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			BoundSql boundSql, ChrExecutor qmpExecutor, Shard shardInterceptor) throws SQLException {
		setTableName(ms, boundSql, parameter, shardInterceptor);
		return super.query(ms, parameter, rowBounds, resultHandler, boundSql, qmpExecutor);
	}

	@Override
	public int update(MappedStatement ms, StatementHandler handler, ChrExecutor qmpExecutor, Shard shardInterceptor)
			throws SQLException {
		setTableName(ms, handler.getBoundSql(), handler.getBoundSql().getParameterObject(), shardInterceptor);
		return super.update(ms, handler, qmpExecutor);
	}

	private void setTableName(MappedStatement ms, BoundSql boundSql, Object parameter, Shard shardInterceptor) {
		int part = shardInterceptor.part();
		if (part > 0) {
			String sql = getSql(boundSql);
			Object obj = getValue(parameter, shardInterceptor.args());
			int tableNumber = Objects.hashCode(obj) % part;
			if (tableNumber < 0) {
				tableNumber = -tableNumber;
			}
			String table = getTable(sql, ms.getSqlCommandType());
			String newTable = Joiner.on(shardInterceptor.join()).join(table, tableNumber);
			sql = sql.replaceFirst(table, newTable);
			if (LOG.isDebugEnabled()) {
				LOG.debug("sql:" + sql + "paramter:" + parameter);
			}
			setSql(boundSql, sql);
		}
	}

}
