package com.kubrick.sbt.web.common.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyService
 * @description:
 * Aware 感知的意思
 * 1、初始化与实例化的区别是什么？
 * @date 2021/2/27 下午10:30
 */
@Slf4j
@Service
public class SpringFactoryUtils implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        this.beanFactory = beanFactory;
    }

    public <T> T getBean(String beanName) {
        return (T) beanFactory.getBean(beanName);
    }
}
