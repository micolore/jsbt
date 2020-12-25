package com.kubrick.sbt.swagger.conf;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassUtil;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.kubrick.sbt.swagger.property.SwaggerProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author k
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public SwaggerProperties swaggerProperties() {
		return new SwaggerProperties();
	}

	@Bean
	public Docket api(SwaggerProperties swaggerProperties, TypeResolver typeResolver) {

		// @formatter:off
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.host(swaggerProperties.getHost())
				.apiInfo(apiInfo(swaggerProperties))
				.groupName(swaggerProperties.getGroupName())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.build()
				.securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext()))
				.pathMapping("/");
		// @formatter:on

		// 加载额外的model
		String[] additionalModelPackage = swaggerProperties.getAdditionalModelPackage();
		if (additionalModelPackage != null && additionalModelPackage.length > 0) {
			for (String scanPackage : additionalModelPackage) {
				// 扫描指定包下，拥有注解 @ApiModel 的 class
				Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(scanPackage,
						ApiModel.class);
				if (CollectionUtil.isEmpty(classes)) {
					continue;
				}
				for (Class<?> aClass : classes) {
					ResolvedType resolve = typeResolver.resolve(aClass);
					docket.additionalModels(resolve);
				}
			}
		}

		return docket;
	}

	/**
	 * 配置默认的全局鉴权策略的开关，通过正则表达式进行匹配；默认匹配所有URL
	 * @return
	 */
	private SecurityContext securityContext() {
		// @formatter:off
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(swaggerProperties()
						.getAuthorization()
						.getAuthRegex()))
				.build();
		// @formatter:on
	}

	/**
	 * 默认的全局鉴权策略
	 * @return
	 */
	private List<SecurityReference> defaultAuth() {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties().getAuthorization().getAuthorizationScopeList()
				.forEach(authorizationScope -> authorizationScopeList
						.add(new AuthorizationScope(authorizationScope.getScope(),
								authorizationScope.getDescription())));
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList
				.size()];
		return Collections.singletonList(SecurityReference.builder()
				.reference(swaggerProperties().getAuthorization().getName())
				.scopes(authorizationScopeList.toArray(authorizationScopes)).build());
	}

	private OAuth securitySchema() {
		ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		swaggerProperties().getAuthorization().getAuthorizationScopeList()
				.forEach(authorizationScope -> authorizationScopeList
						.add(new AuthorizationScope(authorizationScope.getScope(),
								authorizationScope.getDescription())));
		ArrayList<GrantType> grantTypes = new ArrayList<>();
		swaggerProperties().getAuthorization().getTokenUrlList()
				.forEach(tokenUrl -> grantTypes
						.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl)));
		return new OAuth(swaggerProperties().getAuthorization().getName(),
				authorizationScopeList, grantTypes);
	}

	private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
		// @formatter:off
		return new ApiInfoBuilder()
				.title(swaggerProperties.getTitle())
				.description(swaggerProperties.getDescription())
				.license(swaggerProperties.getLicense())
				.licenseUrl(swaggerProperties.getLicenseUrl())
				.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
				.contact(new Contact(swaggerProperties.getContact().getName(),
						swaggerProperties.getContact().getUrl(),
						swaggerProperties.getContact().getEmail()))
				.version(swaggerProperties.getVersion()).build();
		// @formatter:on
	}

}
