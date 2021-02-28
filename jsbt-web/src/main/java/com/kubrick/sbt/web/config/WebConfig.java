package com.kubrick.sbt.web.config;

import com.kubrick.sbt.web.converter.DateConverter;
import com.kubrick.sbt.web.interceptor.mvc.MyAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author k
 * @version 1.0.0
 * @ClassName WebConfig
 * @description:
 * 1、WebMvcConfigurerAdapter 被弃用了
 * @date 2021/2/28 上午10:31
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyAuthInterceptor());
    }

}
