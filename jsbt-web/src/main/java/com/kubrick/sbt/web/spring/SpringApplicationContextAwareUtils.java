package com.kubrick.sbt.web.spring;

import com.kubrick.sbt.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SpringUtils
 * @description: TODO
 * @date 2021/2/27 下午11:02
 */
@Slf4j
@Service
public class SpringApplicationContextAwareUtils implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

}
