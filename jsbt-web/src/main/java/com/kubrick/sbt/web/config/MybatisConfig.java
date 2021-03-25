package com.kubrick.sbt.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MybatisConfig
 * @description: 1、参考 https://blog.csdn.net/lp1052843207/article/details/76034022
 * @date 2021/3/7 下午7:29
 */
@Configuration
@RequiredArgsConstructor
@MapperScan("com.kubrick.sbt.web.mapper")
public class MybatisConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
		innerInterceptor.setDbType(DbType.MYSQL);
		innerInterceptor.setOverflow(true);
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		interceptor.addInnerInterceptor(innerInterceptor);
		return interceptor;
	}

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return mybatisConfiguration -> mybatisConfiguration.setUseGeneratedShortKey(false);
	}

}
