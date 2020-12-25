package com.kubrick.sbt.swagger.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author k
 */
@Data
@ConfigurationProperties("swagger.provider")
public class SwaggerProviderProperties {

	/**
	 * 聚合者的来源，用于控制跨域放行
	 */
	private String aggregatorOrigin = "*";

}
