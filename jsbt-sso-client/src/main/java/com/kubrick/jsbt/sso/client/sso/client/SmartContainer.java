package com.kubrick.jsbt.sso.client.sso.client;

import com.kubrick.jsbt.sso.client.sso.client.constant.SsoConstant;
import com.kubrick.jsbt.sso.client.sso.client.filter.BaseFilter;
import com.kubrick.jsbt.sso.client.sso.client.filter.ParamFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author k
 * @version 1.0.0
 * @ClassName SmartContainer
 * @description: TODO
 * @date 2021/2/7 下午4:33
 */
public class SmartContainer extends ParamFilter implements Filter {

    /** 排除URL */
    protected String excludeUrls;

    private BaseFilter[] filters;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filters == null || filters.length == 0) {
            throw new IllegalArgumentException("filters不能为空");
        }
        for (BaseFilter filter : filters) {
            filter.setAppId(getAppId());
            filter.setAppSecret(getAppSecret());
            filter.setServerUrl(getServerUrl());
            filter.init(filterConfig);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (isExcludeUrl(httpRequest.getServletPath())) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        for (BaseFilter filter : filters) {
            if (!filter.isAccessAllowed(httpRequest, httpResponse)) {
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isExcludeUrl(String url) {
        if (excludeUrls == null || excludeUrls.isEmpty()) {
            return false;
        }

        Map<Boolean, List<String>> map = Arrays.stream(excludeUrls.split(","))
                .collect(Collectors.partitioningBy(u -> u.endsWith(SsoConstant.URL_FUZZY_MATCH)));
        List<String> urlList = map.get(false);
        if (urlList.contains(url)) { // 优先精确匹配
            return true;
        }
        urlList = map.get(true);
        for (String matchUrl : urlList) { // 再进行模糊匹配
            if (url.startsWith(matchUrl.replace(SsoConstant.URL_FUZZY_MATCH, ""))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        if (filters == null || filters.length == 0) {
            return;
        }
        for (BaseFilter filter : filters) {
            filter.destroy();
        }
    }

    public void setExcludeUrls(String excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public void setFilters(BaseFilter... filters) {
        this.filters = filters;
    }
}
