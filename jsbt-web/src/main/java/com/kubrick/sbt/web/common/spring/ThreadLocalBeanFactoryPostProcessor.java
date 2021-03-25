package com.kubrick.sbt.web.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ThreadLocalBeanFactoryPostProcessor
 * @description: TODO
 * @date 2021/2/27 下午11:23
 */
@Component
public class ThreadLocalBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		beanFactory.registerScope("threadLocalScope", new ThreadLocalScope());
	}

}
