package com.bj58.chr.data.interceptor.core;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import com.bj58.chr.data.interceptor.core.ActionFactory.ActionArgs;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ChrExecutor extends BaseExecutor {

	private ActionFactory actionFactory;

	public final static Executor newExecutor(CachingExecutor cachingExecutor) {
		ChrExecutor chrExecutor = null;
		try {
			BaseExecutor baseExecutor = (BaseExecutor) FieldUtils.readField(cachingExecutor, "delegate", true);
			Configuration configuration = (Configuration) FieldUtils.readField(baseExecutor, "configuration", true);
			Transaction transaction = (Transaction) FieldUtils.readField(baseExecutor, "transaction", true);
			chrExecutor = new ChrExecutor(configuration, transaction);
			chrExecutor.actionFactory = ActionFactory.getInstance();
			FieldUtils.writeField(cachingExecutor, "delegate", chrExecutor, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return chrExecutor;
	}

	public ChrExecutor(Configuration configuration, Transaction transaction) {
		super(configuration, transaction);
	}

	public int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
		Configuration configuration = ms.getConfiguration();
		StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null,
				null);
		ActionArgs actionArgs = actionFactory.getActionArgs(ms);
		return actionArgs.getAction().update(ms, handler, this, actionArgs.getShard());
	}

	public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			BoundSql boundSql) throws SQLException {
		ActionArgs actionArgs = actionFactory.getActionArgs(ms);
		return actionArgs.getAction().query(ms, parameter, rowBounds, resultHandler, boundSql, this,
				actionArgs.getShard());
	}

	public List<BatchResult> doFlushStatements(boolean isRollback) throws SQLException {
		return Collections.emptyList();
	}

}
