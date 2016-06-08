package com.bj58.chr.data.utils;

import com.bj58.chr.data.LogFactory;
import com.bj58.chr.data.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务日志统计
 *
 * @author tianshijing
 */
public class Logger58dp {

	// static {
	// LogFactory.setConfig(Path.getCurrentPath() + "/config/log.properties");
	// }

	// private final static Log minLog = LogFactory.getLogger("cv_min");
	// private final static Log hourLog = LogFactory.getLogger("cv_hour");
	private final static Log dayLog = LogFactory.getLogger("cv_day");
	private final static String FIELD_STEP = "\001";
	private final static Logger logger = LoggerFactory.getLogger(Logger58dp.class);

	private Logger58dp() {
	}

	public void cvLogger(Object object, String optionType, String optionModule) {
		try {
			Map<String, Object> map = objectToMap(object);
			if (map != null) {
				writeLogger(map, optionType, optionModule, "chrdb_cvInfoBo");
			}
		} catch (Exception e) {
			logger.error("58db logger write exception", e);
		}
	}

	public void cvLogger(String cvId, long lRefTime, String optionType, String optionModule) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("id", cvId);
			map.put("lRefTime", lRefTime);
			writeLogger(map, optionType, optionModule, "chrdb_cvInfoBo");
		} catch (Exception e) {
			logger.error("58db logger write exception", e);
		}
	}

	private void writeLogger(Map<String, Object> map, String optionType, String optionModule, String tableName)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		// 操作的表
		sb.append(tableName).append(FIELD_STEP);
		// 操作类型
		sb.append(optionType).append(FIELD_STEP);
		// 时间戳
		sb.append(System.currentTimeMillis()).append(FIELD_STEP);
		// 移除敏感信息
		map = removeSensitive(map);
		sb.append(JSONUtils.writeValue(map)).append(FIELD_STEP);
		// 简历id(可不传)
		sb.append("").append(FIELD_STEP);
		// 修改模块
		sb.append(optionModule);
		dayLog.add(sb.toString());
	}

	private Map<String, Object> removeSensitive(Map<String, Object> params) {
		params.remove("email");
		params.remove("idNumbers");
		params.remove("idType");
		params.remove("mobile");
		params.remove("phone");
		params.remove("weibo");
		params.remove("qq");
		params.remove("weixin");
		return params;
	}

	private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.get(obj) != null) {
				map.put(field.getName(), field.get(obj));
			}
		}
		return map;
	}

	public final static Logger58dp getInstance() {
		return Logger58dpHodler.dp;
	}

	static class Logger58dpHodler {
		private final static Logger58dp dp = new Logger58dp();
	}

}
