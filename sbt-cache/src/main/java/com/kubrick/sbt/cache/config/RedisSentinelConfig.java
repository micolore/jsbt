package com.kubrick.sbt.cache.config;

import lombok.Data;
import lombok.ToString;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RedisSentinelConfig
 * @description: TODO
 * @date 2021/1/1 下午9:05
 */
@Data
@ToString
public class RedisSentinelConfig {

	/**
	 * 哨兵master 名称
	 */
	private String master;

	/**
	 * 哨兵节点
	 */
	private String nodes;

	/**
	 * 哨兵配置
	 */
	private boolean masterOnlyWrite;

	/**
	 *
	 */
	private int failMax;

}
