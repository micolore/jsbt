package com.kubrick.sbt.admin.web.client.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.admin.web.auth.SecurityConfig
 * @description: TODO
 * @date 2020/12/24 下午9:11
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.httpBasic().and().authorizeRequests().antMatchers("/actuator/**")
		// .authenticated().anyRequest().permitAll();
		http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
	}

}
