package com.kubrick.sbt.web.datasource;


import com.kubrick.sbt.web.annotation.AppDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author k
 * @version 1.0.0
 * @ClassName DataSourceConfigRegister
 * @description: TODO
 * @date 2021/2/28 下午6:30
 */
@Slf4j
@Component
public class DataSourceConfigRegister implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(AppDataSource.class.getName()));
        log.debug("DataSourceConfig import...");
        if (null != attributes) {
            Object object = attributes.get("datasourceType");
            SupportDatasourceEnum[] supportDatasourceEnums = (SupportDatasourceEnum[]) object;
            for (SupportDatasourceEnum supportDatasourceEnum : supportDatasourceEnums) {
                DataSourceContextHolder.addDatasource(supportDatasourceEnum);
            }
        }
        return new String[0];
    }


}
