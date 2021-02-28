package com.kubrick.sbt.web.interceptor.api;

import com.kubrick.sbt.web.utils.MdcUtil;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RestTemplateInterceptor
 * @description: TODO
 * @date 2021/2/28 上午11:03
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("traceId", MdcUtil.get());

        return execution.execute(request, body);

    }
}
