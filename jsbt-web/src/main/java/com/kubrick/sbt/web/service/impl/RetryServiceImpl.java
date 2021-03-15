package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.service.RetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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


}
