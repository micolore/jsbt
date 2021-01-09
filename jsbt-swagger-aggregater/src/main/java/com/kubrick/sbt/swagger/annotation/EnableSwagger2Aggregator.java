package com.kubrick.sbt.swagger.annotation;

import com.kubrick.sbt.swagger.conf.SwaggerAggregatorAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author k
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerAggregatorAutoConfiguration.class })
public @interface EnableSwagger2Aggregator {

}
