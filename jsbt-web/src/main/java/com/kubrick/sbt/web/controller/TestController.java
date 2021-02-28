package com.kubrick.sbt.web.controller;

import com.kubrick.sbt.web.datasource.SupportDatasourceEnum;
import com.kubrick.sbt.web.datasource.UsingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 * @version 1.0.0
 * @ClassName TestController
 * @description: TODO
 * @date 2021/2/28 下午6:35
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/testDev")
    @UsingDataSource(type = SupportDatasourceEnum.DEV_DB)
    public void testDev() {
        showData();
    }

    @GetMapping(value = "/testPre")
    @UsingDataSource(type = SupportDatasourceEnum.PRE_DB)
    public void testPre() {
        showData();
    }

    private void showData() {
        jdbcTemplate.queryForList("select * from user").forEach(row -> log.info(row.toString()));
    }


}

