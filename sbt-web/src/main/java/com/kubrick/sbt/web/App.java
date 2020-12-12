package com.kubrick.sbt.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author k
 */
@SpringBootApplication
@MapperScan("com.kubrick.sbt.web.dao")
public class App {

    public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }
}
