package com.kubrick.sbt.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author k
 * @version 1.0.0
 * @ClassName InitServiceImpl
 * @description:
 * 1、init-method、PostConstruct 和 InitializingBean 执行顺序是什么？
 *   AbstractAutowireCapableBeanFactory
 * @date 2021/2/27 下午11:18
 */
@Slf4j
@Service
public class InitServiceImpl implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitServiceImpl init...");
    }
}
