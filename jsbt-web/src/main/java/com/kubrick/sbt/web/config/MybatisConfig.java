package com.kubrick.sbt.web.config;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MybatisConfig
 * @description:
 * 1、参考 https://blog.csdn.net/lp1052843207/article/details/76034022
 * @date 2021/3/7 下午7:29
 */
@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addMySqlInterceptor() {
        //SqlStatementInterceptor interceptor = new SqlStatementInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            //sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}

