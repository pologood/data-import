package com.bj58.chr.data.handler.im;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

/**
 *
 *
 * @author minds
 * @version 1.0
 * @date 2016年4月1日 下午2:39:07
 * @see
 * @since
 */
public class MongoDataImport {

	private final static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	@PostConstruct
	public void init() {
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("ttt");
			}
		}, 10, 10, TimeUnit.MINUTES);
	}

}
