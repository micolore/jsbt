package com.kubrick.sbt.web;

import com.kubrick.sbt.job.annotation.EnableXxlJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author k
 */
@SpringBootApplication
@EnableXxlJob
@MapperScan("com.kubrick.sbt.web.dao")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
