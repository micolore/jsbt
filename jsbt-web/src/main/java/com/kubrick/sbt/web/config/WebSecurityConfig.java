package com.kubrick.sbt.web.config;

import cn.hutool.core.util.ArrayUtil;
import com.kubrick.sbt.web.common.auth.handler.*;
import com.kubrick.sbt.web.common.interceptor.PermitAllUrlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PermitAllUrlProperties permitAllUrlProperties;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomFailureHandler customFailureHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;

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

        http
                .authenticationProvider(authenticationProvider())
                .httpBasic()
                //未登录
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                //.antMatchers(ArrayUtil.toArray(permitAllUrlProperties.getIgnoreUrls(), String.class)).permitAll()
                .anyRequest().authenticated() //必须授权才能范围
                .and()
                .formLogin() //使用自带的登录
                .permitAll()
                //登录失败
                .failureHandler(customFailureHandler)
                //登录成功
                .successHandler(customSuccessHandler)
                .and()
                .exceptionHandling()
                //没有权限
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .logout()
                //退出成功
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
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
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
