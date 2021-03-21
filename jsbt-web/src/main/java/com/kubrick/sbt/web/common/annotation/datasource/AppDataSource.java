package com.kubrick.sbt.web.common.annotation.datasource;


import com.kubrick.sbt.web.common.datasource.DataSourceConfigRegister;
import com.kubrick.sbt.web.common.datasource.SupportDatasourceEnum;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AppDataSource
 * @description: TODO
 * @date 2021/2/28 下午6:28
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DataSourceConfigRegister.class)
public @interface AppDataSource {

    SupportDatasourceEnum[] datasourceType();
}
