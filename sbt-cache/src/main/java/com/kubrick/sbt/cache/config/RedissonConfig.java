package com.kubrick.sbt.cache.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RedissonConfig
 * @description: TODO
 * @date 2020/12/29 上午12:49
 */
@Configuration
public class RedissonConfig {

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer()
		.setAddress("redis://localhost:6379")
		//发布和订阅连接池大小
		.setSubscriptionConnectionPoolSize(20)
		//发布和订阅连接的最小空闲连接数
		.setSubscriptionConnectionMinimumIdleSize(30)
		//接池大小
		.setConnectionPoolSize(50)
		//最小空闲连接数
		.setConnectionMinimumIdleSize(10)
		//命令等待超时
		.setTimeout(1500)
		//命令重试发送时间间隔
		.setRetryInterval(300)
		//命令失败重试次数
		.setRetryAttempts(3)
		.setConnectTimeout(2000)
		.setIdleConnectionTimeout(3000);
		return Redisson.create(config);
	}

}
