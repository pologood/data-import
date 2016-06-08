package com.bj58.chr.data.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import com.bj58.chr.data.utils.RedisHeper;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午11:08:36
 * @see
 * @since
 */
public class JVMCache implements Cache {

	private static boolean init = false;
	private String id;

	private Map<Object, Object> cache = new ConcurrentHashMap<Object, Object>();

	public JVMCache(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public int getSize() {
		init();
		return cache.size();
	}

	public void putObject(Object key, Object value) {
		init();
		cache.put(key, value);
	}

	public Object getObject(Object key) {
		init();
		return cache.get(key);
	}

	public Object removeObject(Object key) {
		init();
		return cache.remove(key);
	}

	// 有变化，就通知所有变化
	public void clear() {
		cache.clear();
		RedisHeper.getRedisHeper().publish(id);
	}

	private void init() {
		if (!init) {
			RedisHeper.getRedisHeper().register(id, this);
			init = true;
		}
	}

	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

	public void change() {
		cache.clear();
	}

}
