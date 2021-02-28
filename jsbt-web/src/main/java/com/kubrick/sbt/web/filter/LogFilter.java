package com.kubrick.sbt.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName LogFilter
 * @description: TODO
 * @date 2021/2/28 上午10:45
 */
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter start...");
        chain.doFilter(request, response);
        log.info("doFilter end...");
    }

    @Override
    public void destroy() {

    }

}
