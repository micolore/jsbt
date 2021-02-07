package com.kubrick.jsbt.sso.client.filter;

import com.kubrick.jsbt.sso.client.listener.LogoutListener;
import com.kubrick.jsbt.sso.client.session.SessionMappingStorage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName BaseFilter
 * @description: TODO
 * @date 2021/2/7 下午3:25
 */
public abstract class BaseFilter extends ParamFilter implements Filter {

    private SessionMappingStorage sessionMappingStorage;

    public abstract boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response)
            throws IOException;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException {
    }

    @Override
    public void destroy() {
    }

    protected SessionMappingStorage getSessionMappingStorage() {
        if (sessionMappingStorage == null) {
            sessionMappingStorage = LogoutListener.getSessionMappingStorage();
        }
        return sessionMappingStorage;
    }
}
