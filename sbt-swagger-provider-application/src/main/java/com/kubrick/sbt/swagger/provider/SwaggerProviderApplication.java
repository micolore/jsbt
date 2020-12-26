package com.kubrick.sbt.swagger.provider;

import com.kubrick.sbt.swagger.annotation.EnableSwagger2Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.swagger.SwaggerApplication
 * @description: TODO
 * @date 2020/12/26 上午12:28
 */
@SpringBootApplication
@EnableSwagger2Provider
@ComponentScan(value = "com.kubrick.sbt")
public class SwaggerProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerProviderApplication.class, args);
	}

}
