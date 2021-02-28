package com.kubrick.sbt.web;

import com.kubrick.sbt.web.datasource.AppDataSource;
import com.kubrick.sbt.web.datasource.SupportDatasourceEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author k
 */
//@EnableLog
//@ForestScan(basePackages = "com.kubrick.sbt.web.api")
@EnableAsync
@SpringBootApplication
@MapperScan("com.kubrick.sbt.web.dao")
@AppDataSource(datasourceType = {SupportDatasourceEnum.DEFAULT_DB, SupportDatasourceEnum.DEV_DB, SupportDatasourceEnum.PRE_DB, SupportDatasourceEnum.PROD_DB})
public class WebApp {

    public static void main(String[] args) {
        disableWarning();
        SpringApplication.run(WebApp.class, args);
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


