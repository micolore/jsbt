package com.kubrick.sbt.web;

import com.kubrick.sbt.web.annotation.datasource.AppDataSource;
import com.kubrick.sbt.web.datasource.SupportDatasourceEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author k
 */
@EnableRetry
@EnableAsync
@SpringBootApplication
@ComponentScan("com.kubrick.*")
@MapperScan("com.kubrick.sbt.web.dao")
@AppDataSource(datasourceType = {SupportDatasourceEnum.DEFAULT_DB, SupportDatasourceEnum.DEV_DB, SupportDatasourceEnum.PRE_DB, SupportDatasourceEnum.PROD_DB})
public class WebApplication {

    public static void main(String[] args) {
        disableWarning();
        SpringApplication.run(WebApplication.class, args);
    }

    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


