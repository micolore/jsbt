package com.kubrick.sbt.cache.redisson.config;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CacheConfiguration
 * @description: TODO
 * @date 2021/1/1 下午9:08
 */
@EnableConfigurationProperties(RedissonConfig.class)
@Configuration
@RequiredArgsConstructor
public class CacheConfiguration {

	private final String CODEC = "org.redisson.codec.JsonJacksonCodec";

	final RedissonConfig redisProperties;

	@Configuration
	@ConditionalOnClass({ Redisson.class })
	@ConditionalOnExpression("'${spring.redis.mode}'=='single' or '${spring.redis.mode}'=='cluster' or '${spring.redis.mode}'=='sentinel'")
	protected class RedissonSingleClientConfiguration {

		/**
		 * 单机模式 redisson 客户端
		 */
		@Bean
		@ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
		RedissonClient redissonSingle() throws Exception {
			Config config = new Config();
			//config.setTransportMode(TransportMode.NIO);
			Codec codec = (Codec) ClassUtils
					.forName(CODEC, ClassUtils.getDefaultClassLoader()).newInstance();
			config.setCodec(codec);
			String node = redisProperties.getSingle().getAddress();
			node = node.startsWith("redis://") ? node : "redis://" + node;
			SingleServerConfig serverConfig = config.useSingleServer().setAddress(node)
					.setTimeout(redisProperties.getPool().getConnTimeout())
					.setConnectionPoolSize(redisProperties.getPool().getSize())
					.setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle());
			if (StringUtils.isNotBlank(redisProperties.getPassword())) {
				serverConfig.setPassword(redisProperties.getPassword());
			}
			return Redisson.create(config);
		}

		/**
		 * 集群模式的 redisson 客户端
		 * @return
		 */
		@Bean
		@ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
		RedissonClient redissonCluster() {
			System.out.println("cluster redisProperties:" + redisProperties.getCluster());
			Config config = new Config();
			String[] nodes = redisProperties.getCluster().getNodes().split(",");
			List<String> newNodes = new ArrayList(nodes.length);
			Arrays.stream(nodes).forEach((index) -> newNodes
					.add(index.startsWith("redis://") ? index : "redis://" + index));

			ClusterServersConfig serverConfig = config.useClusterServers()
					.addNodeAddress(newNodes.toArray(new String[0]))
					.setScanInterval(redisProperties.getCluster().getScanInterval())
					.setIdleConnectionTimeout(redisProperties.getPool().getSoTimeout())
					.setConnectTimeout(redisProperties.getPool().getConnTimeout())
					.setRetryAttempts(redisProperties.getCluster().getRetryAttempts())
					.setRetryInterval(redisProperties.getCluster().getRetryInterval())
					.setMasterConnectionPoolSize(
							redisProperties.getCluster().getMasterConnectionPoolSize())
					.setSlaveConnectionPoolSize(
							redisProperties.getCluster().getSlaveConnectionPoolSize())
					.setTimeout(redisProperties.getTimeout());
			if (StringUtils.isNotBlank(redisProperties.getPassword())) {
				serverConfig.setPassword(redisProperties.getPassword());
			}
			return Redisson.create(config);
		}

		/**
		 * 哨兵模式 redisson 客户端
		 * @return
		 */

		@Bean
		@ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
		RedissonClient redissonSentinel() {
			System.out
					.println("sentinel redisProperties:" + redisProperties.getSentinel());
			Config config = new Config();
			String[] nodes = redisProperties.getSentinel().getNodes().split(",");
			List<String> newNodes = new ArrayList(nodes.length);
			Arrays.stream(nodes).forEach((index) -> newNodes
					.add(index.startsWith("redis://") ? index : "redis://" + index));

			SentinelServersConfig serverConfig = config.useSentinelServers()
					.addSentinelAddress(newNodes.toArray(new String[0]))
					.setMasterName(redisProperties.getSentinel().getMaster())
					.setReadMode(ReadMode.SLAVE).setTimeout(redisProperties.getTimeout())
					.setMasterConnectionPoolSize(redisProperties.getPool().getSize())
					.setSlaveConnectionPoolSize(redisProperties.getPool().getSize());

			if (StringUtils.isNotBlank(redisProperties.getPassword())) {
				serverConfig.setPassword(redisProperties.getPassword());
			}

			return Redisson.create(config);
		}

	}

}
