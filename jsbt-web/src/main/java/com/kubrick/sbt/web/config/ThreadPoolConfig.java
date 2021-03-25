package com.kubrick.sbt.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ThreadPoolConfig
 * @description: 1、增强spring 异步（线程池）
 * @date 2021/2/28 上午11:16
 */
@Configuration
public class ThreadPoolConfig {

	@Value("${thread.pool.corePoolSize:5}")
	private int corePoolSize;

	@Value("${thread.pool.maxPoolSize:10}")
	private int maxPoolSize;

	@Value("${thread.pool.queueCapacity:200}")
	private int queueCapacity;

	@Value("${thread.pool.keepAliveSeconds:30}")
	private int keepAliveSeconds;

	@Value("${thread.pool.threadNamePrefix:jsbt-async-}")
	private String threadNamePrefix;

	@Bean
	public Executor MessageExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setThreadNamePrefix(threadNamePrefix);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

}
