package com.bj58.chr.data.interceptor.core;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.bj58.chr.data.interceptor.impl.DateShardAction;
import com.bj58.chr.data.interceptor.impl.DefaultAction;
import com.bj58.chr.data.interceptor.impl.HashShardAction;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory implements ApplicationContextAware {

	private static Logger LOG = LoggerFactory.getLogger(ActionFactory.class);

	private static ActionFactory instance = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (instance == null) {
			MapperScannerConfigurer mapperScannerConfigurer = applicationContext.getBean(MapperScannerConfigurer.class);
			try {
				String basePackage = (String) FieldUtils.readField(mapperScannerConfigurer, "basePackage", true);
				basePackage = System.getProperty("os.name").startsWith("Win")?basePackage.replaceAll("\\.", "\\\\"):basePackage.replaceAll("\\.", "/");
				String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + basePackage + "/"
						+ FieldUtils.readStaticField(ClassPathScanningCandidateComponentProvider.class,
								"DEFAULT_RESOURCE_PATTERN", true);
				ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

				Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

				regiseter(resources, basePackage);
			} catch (IllegalAccessException e) {
				LOG.error("reg error", e);
			} catch (InstantiationException e) {
				LOG.error("reg error", e);
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = this;
		}
	}

	public final static ActionFactory getInstance() {
		return instance;
	}

	private Map<String, ActionArgs> mappers = new HashMap<String, ActionArgs>();

	private Action defaultAction = new DefaultAction();

	private ActionArgs defaultActionArgs = new ActionArgs(defaultAction, null);

	public void regiseter(Resource[] resources, String basePackage)
			throws IllegalAccessException, InstantiationException {
		if (resources != null) {
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					String path;
					try {
						path = resource.getFile().getAbsolutePath();
						path = path.substring(path.indexOf(basePackage));
						path = System.getProperty("os.name").startsWith("Win")?path.replaceAll("\\\\", "/"):path;
						Class<?> cls = ClassUtils.getClass(path.replaceAll("\\/", ".").replace(".class", ""));

						Shard shard = AnnotationUtils.getAnnotation(cls, Shard.class);
						if (shard != null) {
							Action action = null;
							switch (shard.shardType()) {
							case HASH:
								action = new HashShardAction();
								break;
							case DATE:
								action = new DateShardAction();
								break;
							default:
								action = defaultAction;
								break;
							}
							ActionArgs actionArgs = new ActionArgs(action, shard);
							mappers.put(cls.getName(), actionArgs);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		mappers = Collections.unmodifiableMap(mappers);

	}

	public ActionArgs getActionArgs(MappedStatement ms) {
		String id = ms.getId();
		String className = id.substring(0, id.lastIndexOf("."));
		ActionArgs actionArgs = mappers.get(className);
		if (actionArgs == null) {
			actionArgs = defaultActionArgs;
		}
		return actionArgs;
	}

	class ActionArgs {

		private Action action;
		private Shard shard;

		public ActionArgs(Action action, Shard shardInterceptor) {
			this.action = action;
			this.shard = shardInterceptor;
		}

		public Action getAction() {
			return action;
		}

		public void setAction(Action action) {
			this.action = action;
		}

		public Shard getShard() {
			return shard;
		}

		public void setShard(Shard shard) {
			this.shard = shard;
		}

	}

}
