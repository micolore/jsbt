package com.kubrick.sbt.cache.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RedissonConfig
 * @description: TODO
 * @date 2020/12/29 上午12:49
 */
@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
@Data
@ToString
public class RedissonConfig {

	private int database;

	/**
	 * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
	 */
	private int timeout;

	private String password;

	private String mode;

	/**
	 * 池配置
	 */
	private RedissonPoolConfig pool;

	/**
	 * 单机信息配置
	 */
	private RedissonSingleConfig single;

	/**
	 * 集群 信息配置
	 */
	private RedissonClusterConfig cluster;

	/**
	 * 哨兵配置
	 */
	private RedisSentinelConfig sentinel;

}
