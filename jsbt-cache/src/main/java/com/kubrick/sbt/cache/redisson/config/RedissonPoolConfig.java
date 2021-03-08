package com.kubrick.sbt.cache.redisson.config;

import lombok.Data;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RedisPoolConfig
 * @description: TODO
 * @date 2021/1/1 下午9:03
 */
@Data
public class RedissonPoolConfig {

	private int maxIdle;

	private int minIdle;

	private int maxActive;

	private int maxWait;

	private int connTimeout;

	private int soTimeout;

	/**
	 * 池大小
	 */
	private int size;

}
