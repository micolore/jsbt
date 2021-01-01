package com.kubrick.sbt.web.config;

import com.kubrick.sbt.web.auth.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author k 1、prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..]
 * 2、secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用 3、jsr250Enabled ：决定 JSR-250
 * annotations 注解[@RolesAllowed..] 是否可用. 4、开启 Spring Security
 * 方法级安全注解 @EnableGlobalMethodSecurity
 */
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 静态资源设置 v1 add Knife4j static file and api v2 add actuator
	 */
	@Override
	public void configure(WebSecurity webSecurity) {
		// 不拦截静态资源,所有用户均可访问的资源
		webSecurity.ignoring().antMatchers("/", "/css/**", "/js/**", "/images/**",
				"/layui/**", "/v2/api-docs", "/doc.html", "/webjars/**",
				"/swagger-resources/**", "/actuator/**");
	}

	/**
	 * http请求设置
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		/*
		 * 解决 in a frame because it set 'X-Frame-Options' to 'DENY' 问题
		 */
		http.headers().frameOptions().disable();
		/*
		 * 注释就是使用 csrf 功能
		 */
		// http.csrf().disable();

		http.headers().contentTypeOptions().disable();
		// http.anonymous().disable();
		http.authorizeRequests()
				/**
				 * 不拦截登录相关方法
				 */
				.antMatchers("/login/**", "/initUserData").permitAll()
				// .antMatchers("/user").hasRole("ADMIN") // user接口只有ADMIN角色的可以访问
				// .anyRequest()
				// .authenticated()// 任何尚未匹配的URL只需要验证用户即可访问
				.anyRequest()
				/**
				 * 根据账号权限访问
				 */
				.access("@rbacPermission.hasPermission(request, authentication)").and()
				.formLogin().loginPage("/")
				/**
				 * 登录请求页
				 */
				.loginPage("/login")
				/**
				 * 登录POST请求路径
				 */
				.loginProcessingUrl("/login")
				/**
				 * 用户名
				 */
				.usernameParameter("username")
				/**
				 * 登录密码参数
				 */
				.passwordParameter("password")
				/**
				 * 默认登录成功页面
				 */
				.defaultSuccessUrl("/main").and().exceptionHandling()
				/**
				 * 无权限处理器
				 */
				.accessDeniedHandler(customAccessDeniedHandler).and().logout()
				/**
				 * 退出登录成功URL
				 */
				.logoutSuccessUrl("/login?logout");

	}

	/**
	 * 自定义获取用户信息接口
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * 密码加密算法
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
