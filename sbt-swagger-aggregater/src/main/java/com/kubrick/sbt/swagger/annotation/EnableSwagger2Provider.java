package com.kubrick.sbt.swagger.annotation;

import com.kubrick.sbt.swagger.conf.SwaggerProviderAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author k
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerProviderAutoConfiguration.class })
public @interface EnableSwagger2Provider {

}
