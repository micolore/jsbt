package com.kubrick.sbt.web.spring;

import com.kubrick.sbt.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SpringApplicationContextUtils
 * @description: TODO
 * @date 2021/2/27 下午11:05
 */
@Slf4j
@Service
public class SpringApplicationContextUtils implements ApplicationListener<ContextRefreshedEvent> {
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        applicationContext = event.getApplicationContext();
    }

    public <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

}
