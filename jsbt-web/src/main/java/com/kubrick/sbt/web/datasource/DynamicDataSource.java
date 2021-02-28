package com.kubrick.sbt.web.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author k
 * @version 1.0.0
 * @ClassName DynamicDataSource
 * @description: TODO
 * @date 2021/2/28 下午6:32
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContextHolder.getDatabaseHolder();
        return dataSource;
    }
}

