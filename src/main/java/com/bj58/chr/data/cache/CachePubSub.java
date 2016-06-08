package com.bj58.chr.data.cache;

import redis.clients.jedis.JedisPubSub;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月2日 下午11:28:13
 * @see
 * @since
 */
public abstract class CachePubSub extends JedisPubSub {

	@Override
	public abstract void onMessage(String channel, String message);

	@Override
	public void onPMessage(String pattern, String channel, String message) {
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {

	}

}
