package com.bj58.chr.data.interceptor.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public abstract class BaseAction implements Action {

	private final static Map<String, String> sqlMapTable = new ConcurrentHashMap<>();

	private final static Pattern DELETE = Pattern.compile(
			"[dD]{1}[eE]{1}[lL]{1}[eE]{1}[tT]{1}[eE]{1}\\s{1,}[fF]{1}[rR]{1}[oO]{1}[mM]{1}\\s{1,}(\\w*)\\s{1,}");

	private final static Pattern SELECT = Pattern.compile("[fF]{1}[rR]{1}[oO]{1}[mM]{1}\\s{1,}(\\w*)\\s{1,}");

	private final static Pattern UPDATE = Pattern
			.compile("[uU]{1}[pP]{1}[dD]{1}[aA]{1}[tT]{1}[eE]{1}\\s{1,}(\\w*)\\s{1,}");

	private final static Pattern INSERT = Pattern.compile(
			"[iI]{1}[nN]{1}[sS]{1}[eE]{1}[rR]{1}[tT]{1}\\s{1,}[iI]{1}[nN]{1}[tT]{1}[oO]+\\s{1,}(\\w*)\\s?[^(]");

	public Connection getConnection(ChrExecutor qmpExecutor, Log statementLog) {
		try {
			return qmpExecutor.getTransaction().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	public int update(MappedStatement ms, StatementHandler handler, ChrExecutor qmpExecutor) throws SQLException {
		Statement stmt = null;
		try {
			stmt = prepareStatement(qmpExecutor, handler, ms.getStatementLog());
			return handler.update(stmt);
		} finally {
			closeStatement(stmt);
		}
	}

	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			BoundSql boundSql, ChrExecutor qmpExecutor) throws SQLException {
		Statement stmt = null;
		try {
			Configuration configuration = ms.getConfiguration();
			StatementHandler handler = configuration.newStatementHandler(qmpExecutor, ms, parameter, rowBounds,
					resultHandler, boundSql);
			stmt = prepareStatement(qmpExecutor, handler, ms.getStatementLog());
			return handler.<E> query(stmt, resultHandler);
		} finally {
			closeStatement(stmt);
		}
	}

	private Statement prepareStatement(ChrExecutor qmpExecutor, StatementHandler handler, Log statementLog)
			throws SQLException {
		Statement stmt;
		Connection connection = getConnection(qmpExecutor, statementLog);
		stmt = handler.prepare(connection);
		handler.parameterize(stmt);
		return stmt;
	}

	protected String getSql(BoundSql boundSql) {
		try {
			return (String) FieldUtils.readField(boundSql, "sql", true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void setSql(BoundSql boundSql, String sql) {
		try {
			FieldUtils.writeDeclaredField(boundSql, "sql", sql, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected String getSql(StatementHandler handler) {
		return getSql(handler.getBoundSql());
	}

	protected void setSql(StatementHandler handler, String sql) {
		setSql(handler.getBoundSql(), sql);
	}

	/**
	 * 得到sql语句中的参数值 根据传递过来的key
	 * 
	 * @param handler
	 * @param paramKey
	 *            参数名称
	 * @return
	 */
	protected Object getParameterObject(StatementHandler handler, String paramKey) {
		return getParameterObject(handler.getParameterHandler(), paramKey);

	}

	protected Object getParameterObject(ParameterHandler parameterHandler, String paramKey) {
		try {
			return FieldUtils.readField(parameterHandler.getParameterObject(), paramKey, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Object getValue(Object parameter, String field) {
		if (parameter instanceof Map<?, ?>) {
			return ((Map<?, ?>) parameter).get(field);
		} else {
			try {
				return FieldUtils.readField(parameter, field, true);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	protected String getTable(String sql, SqlCommandType sqlCommandType) {
		String table = sqlMapTable.get(sql);
		if (table == null) {
			Pattern pattern = null;
			if (sqlCommandType == SqlCommandType.DELETE) {
				pattern = DELETE;
			} else if (sqlCommandType == SqlCommandType.INSERT) {
				pattern = INSERT;
			} else if (sqlCommandType == SqlCommandType.SELECT) {
				pattern = SELECT;
			} else if (sqlCommandType == SqlCommandType.UPDATE) {
				pattern = UPDATE;
			}
			if (pattern != null) {
				Matcher mt = pattern.matcher(sql);
				if (mt.find()) {
					table = mt.group(1);
				}
				if (!StringUtils.isEmpty(table)) {
					sqlMapTable.put(sql, table);
				}
			}
		}
		return table;
	}

}
