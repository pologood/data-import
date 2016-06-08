package com.bj58.chr.data.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.bj58.chr.common.ResourceUtils;
import com.bj58.chr.data.cache.CachePubSub;
import com.bj58.chr.data.cache.JVMCache;
import com.google.common.collect.Maps;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午11:26:29
 * @see
 * @since
 */
public class RedisHeper {

	private JedisPool jedisPool;

	private final static String CHANNEL = "qd_cache_update_channel";

	private final static String LOCK = "qd_import_lock";

	private final static String RECENT = "qd_import_recent";

	private final static ConcurrentHashMap<String, JVMCache> JVM_MAP = new ConcurrentHashMap<>();

	private final static ExecutorService executorService = Executors.newSingleThreadExecutor();

	private final static RedisHeper INSTANCE = new RedisHeper();

	public final static RedisHeper getRedisHeper() {
		return INSTANCE;
	}

	private static int DELAY;

	private RedisHeper() {
		Properties properties = new Properties();
		try {
			properties.load(ResourceUtils.getResource("redis.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jedisPool = new JedisPool(properties.getProperty("redis.host", "localhost"),
				NumberUtils.toInt(properties.getProperty("redis.port"), 1935));

		DELAY = NumberUtils.toInt(properties.getProperty("delay.time"), 600);

		executorService.submit(new Runnable() {
			@Override
			public void run() {
				Jedis jedis = null;
				try {
					jedis = jedisPool.getResource();
					jedis.subscribe(cachePubSub, CHANNEL);
				} finally {
					jedisPool.returnResource(jedis);
				}
			}
		});
	}

	private CachePubSub cachePubSub = new CachePubSub() {
		@Override
		public void onMessage(String channel, String message) {
			JVMCache jvmCache = JVM_MAP.get(message);
			if (jvmCache != null) {
				jvmCache.change();
			}
		}
	};

	/**
	 * @param id
	 */
	public void publish(String message) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.publish(CHANNEL, message);
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public boolean lock() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (jedis.setnx(LOCK, DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")) == 1) {
				jedis.expire(LOCK, DELAY);
				return true;
			}
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public long getRecent() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return NumberUtils.toLong(jedis.get(RECENT));
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public void setRecent(long last) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(RECENT, String.valueOf(last));
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * @param id
	 * @param jvmCache
	 */
	public void register(String id, JVMCache jvmCache) {
		JVM_MAP.putIfAbsent(id, jvmCache);
	}

	public void set(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, String.valueOf(obj));
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * @param key
	 */
	public long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public long getIncr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return NumberUtils.toLong(jedis.get(key));
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	public Map<String, Response<String>> getIncr(String... keys) {
		Jedis jedis = null;
		Map<String, Response<String>> map = Maps.newHashMap();
		try {
			jedis = jedisPool.getResource();
			Pipeline pipeline = jedis.pipelined();
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				Response<String> resp = pipeline.get(key);
				map.put(key, resp);
			}
			pipeline.sync();
		} finally {
			jedisPool.returnResource(jedis);
		}
		return map;
	}

	public String setex(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.setex(key, seconds, value);
		} finally {
			jedisPool.returnResource(jedis);
		}
	}
}
