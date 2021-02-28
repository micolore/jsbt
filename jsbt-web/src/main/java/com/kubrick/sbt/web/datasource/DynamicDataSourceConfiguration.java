package com.kubrick.sbt.web.datasource;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;


/**
 * @author k
 * @version 1.0.0
 * @ClassName DynamicDataSourceConfiguration
 * @description: TODO
 * @date 2021/2/28 下午6:32
 */
@Slf4j
@Component
public class DynamicDataSourceConfiguration {

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        log.debug("init datasource");
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> dataSourcesMap = new HashMap<>();
        HashSet<SupportDatasourceEnum> dataSet = DataSourceContextHolder.getDataSourceSet();
        for (SupportDatasourceEnum supportDatasourceEnum : dataSet) {
            DataSource dataSource = this.createDataSourceProperties(supportDatasourceEnum);
            dataSourcesMap.put(supportDatasourceEnum.toString(), dataSource);
        }
        dynamicDataSource.setTargetDataSources(dataSourcesMap);
        dynamicDataSource.setDefaultTargetDataSource(createDataSourceProperties(SupportDatasourceEnum.DEFAULT_DB));
        return dynamicDataSource;
    }

    private synchronized DataSource createDataSourceProperties(SupportDatasourceEnum supportDatasourceEnum) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(supportDatasourceEnum.getUrl());
        druidDataSource.setUsername(supportDatasourceEnum.getUsername());
        druidDataSource.setPassword(supportDatasourceEnum.getPassword());
        druidDataSource.setMaxActive(100);
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxWait(30000);
        druidDataSource.setTimeBetweenConnectErrorMillis(60000);
        return druidDataSource;
    }

}
