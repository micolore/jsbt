package com.kubrick.sbt.swagger.conf;

import com.kubrick.sbt.swagger.property.SwaggerAggregatorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.util.CollectionUtils;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k
 */
@Import(SwaggerConfiguration.class)
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerAggregatorAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public SwaggerAggregatorProperties swaggerAggregatorProperties() {
		return new SwaggerAggregatorProperties();
	}

	/**
	 * 聚合文档
	 * @param defaultResourcesProvider
	 * @return
	 */
	@Primary
	@Bean
	@ConditionalOnBean(SwaggerAggregatorProperties.class)
	public SwaggerResourcesProvider swaggerResourcesProvider(
			InMemorySwaggerResourcesProvider defaultResourcesProvider,
			SwaggerAggregatorProperties swaggerAggregatorProperties) {

		return () -> {
			// 聚合者自己的 Resources
			List<SwaggerResource> resources = new ArrayList<>(
					defaultResourcesProvider.get());
			// 提供者的 Resources
			List<SwaggerResource> providerResources = swaggerAggregatorProperties
					.getProviderResources();
			if (!CollectionUtils.isEmpty(providerResources)) {
				resources.addAll(providerResources);
			}
			return resources;
		};
	}

}
