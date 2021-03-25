package com.kubrick.sbt.web.service.impl;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.kubrick.sbt.web.service.RetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RetryServiceImpl
 * @description: TODO
 * @date 2021/3/15 下午8:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RetryServiceImpl implements RetryService {

	private final RestTemplate restTemplate;

	@Override
	public void retryHttp() {
		log.info("retryHttp execute...");
		String resp = restTemplate.getForObject("http://www.baidu.com", String.class);
		System.out.println("resp:" + resp);
		int x = 1 / 0;

	}

	@Override
	public void recoverRetryHttp(ArithmeticException exception) {
		log.info("recoverRetryHttp execute...");
		log.error("recoverRetryHttp error:{}", exception.getMessage(), exception);
	}

	/**
	 * guava retry
	 */
	@Override
	public Boolean guavaRetry() throws Exception {
		Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder().retryIfException().retryIfRuntimeException()
				.retryIfExceptionOfType(Exception.class).retryIfException(Predicates.equalTo(new Exception()))
				.retryIfResult(Predicates.equalTo(false))

				.withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
				.withStopStrategy(StopStrategies.stopAfterAttempt(6))
				.withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS)).build();
		// 定义请求实现
		Callable<Boolean> callable = new Callable<Boolean>() {
			int times = 1;

			@Override
			public Boolean call() throws Exception {
				log.info("guavaRetry call times={}", times);
				times++;

				if (times == 2) {
					throw new NullPointerException();
				}
				else if (times == 3) {
					throw new Exception();
				}
				else if (times == 4) {
					throw new RuntimeException();
				}
				else if (times == 5) {
					return false;
				}
				else {
					return true;
				}

			}
		};
		return retryer.call(callable);
	}

}
