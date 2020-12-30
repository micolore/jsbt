package com.kubrick.sbt.cache.service.impl;

import com.kubrick.sbt.cache.service.CacheService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author k
 * @version 1.0.0
 * @ClassName CacheServiceImpl
 * @description: TODO
 * @date 2020/12/29 上午1:02
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private RedissonClient redissonClient;

	@Override
	public void testRedisson() {
		System.out.println("get lock start");
		RLock rl = redissonClient.getLock("rl");
		System.out.println("get lock success");

	}

}
