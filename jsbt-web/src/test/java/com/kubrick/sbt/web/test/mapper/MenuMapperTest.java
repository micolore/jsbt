package com.kubrick.sbt.web.test.mapper;

import com.kubrick.sbt.web.domain.entity.Menu;
import com.kubrick.sbt.web.mapper.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MenuMapperTest
 * @description: test menuMapper
 * @date 2021/3/21 下午5:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuMapperTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testDataSource() {
        System.out.println(dataSource);
    }

    @Test
    public void listMenu() {
        Menu menu = menuMapper.selectById(1);
        System.out.println("listMenu:" + menu);
    }
}
