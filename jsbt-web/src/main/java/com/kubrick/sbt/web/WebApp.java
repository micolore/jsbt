package com.kubrick.sbt.web;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author k
 */
@SpringBootApplication
@MapperScan("com.kubrick.sbt.web.dao")
@ForestScan(basePackages = "com.kubrick.sbt.web.api")
public class WebApp {

	public static void main(String[] args) {
		SpringApplication.run(WebApp.class, args);
	}

}
