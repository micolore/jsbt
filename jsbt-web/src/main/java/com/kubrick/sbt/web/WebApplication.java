package com.kubrick.sbt.web;

import com.kubrick.sbt.web.common.annotation.datasource.AppDataSource;
import com.kubrick.sbt.web.common.datasource.SupportDatasourceEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author k
 */
@EnableRetry
@EnableAsync
@SpringBootApplication
@ComponentScan("com.kubrick.*")
@AppDataSource(datasourceType = { SupportDatasourceEnum.DEFAULT_DB, SupportDatasourceEnum.DEV_DB,
		SupportDatasourceEnum.PRE_DB, SupportDatasourceEnum.PROD_DB })
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
