package com.kubrick.sbt.web.config;

import com.kubrick.sbt.web.common.filter.LogFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogFilterWebConfig
 * @description: TODO
 * @date 2021/2/28 上午10:45
 */
@ConditionalOnWebApplication
public class LogFilterWebConfig {
    @Bean
    public LogFilter timerFilter() {
        return new LogFilter();
    }
}
