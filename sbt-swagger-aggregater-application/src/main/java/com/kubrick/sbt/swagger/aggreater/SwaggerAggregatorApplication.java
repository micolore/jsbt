package com.kubrick.sbt.swagger.aggreater;

import com.kubrick.sbt.swagger.annotation.EnableSwagger2Aggregator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.swagger.SwaggerApplication
 * @description: TODO
 * @date 2020/12/26 上午12:28
 */
@SpringBootApplication
@EnableSwagger2Aggregator
public class SwaggerAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerAggregatorApplication.class, args);
	}

}
