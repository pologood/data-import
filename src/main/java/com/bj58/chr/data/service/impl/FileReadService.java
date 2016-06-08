package com.bj58.chr.data.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.chr.data.utils.JSONUtils;
import com.bj58.chr.data.utils.NamedThreadFactory;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年5月13日 下午5:50:19
 * @see
 * @since
 */
public abstract class FileReadService<T> {

	private final static Logger LOG = LoggerFactory.getLogger(FileReadService.class);

	private final static ExecutorService importTaskService = Executors.newFixedThreadPool(10,
			new NamedThreadFactory("uptime data-"));

	private Class<T> cls;

	@SuppressWarnings("unchecked")
	public FileReadService() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		cls = (Class<T>) type.getActualTypeArguments()[0];
	}

	public void update(String path, int sleep) {

		File file = new File(path);
		LOG.info(file.getAbsolutePath());
		if (file.exists()) {
			BufferedReader fos = null;
			try {
				fos = new BufferedReader(new FileReader(file));
				StringBuilder build = new StringBuilder();
				String line = null;
				while ((line = fos.readLine()) != null) {
					if (line.startsWith("{")) {
						build = new StringBuilder();
						build.append(line);
					} else if (line.endsWith("}")) {
						build.append(line);
						if (sleep > 0) {
							Thread.sleep(sleep);
						}
						handlerFile(build.toString());
					} else {
						if (!line.startsWith("Status")) {
							build.append(line);
						}
					}
				}
				LOG.info(String.format("path is %s updateData analyse finish", path));
			} catch (Exception e) {
				LOG.error("", e);
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
				}
			}
		}
	}

	public abstract void handlerFile(T t);

	public void handlerFile(final String content) {
		importTaskService.submit(new Runnable() {
			@Override
			public void run() {
				T t;
				try {
					t = JSONUtils.readValue(content, cls);
					if (t != null) {
					}
				} catch (Exception e) {
					LOG.error("", e);
				}
			}
		});
	}

}
