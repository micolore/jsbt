package com.kubrick.sbt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Knife4jConfiguration
 * @description: TODO
 * @date 2020/12/19 下午10:20
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

	@Bean(value = "defaultApi")
	public Docket defaultApi2() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder().title("sbt  RESTful APIs").description("# sbt RESTful APIs")
						.termsOfServiceUrl("http://localhost:10001").version("1.0").build())
				// 分组名称
				.groupName("0.0.1版本").select()
				// 这里指定Controller扫描包路径
				.apis(RequestHandlerSelectors.basePackage("com.kubrick.sbt.web.controller")).paths(PathSelectors.any())
				.build();
		return docket;
	}

}
