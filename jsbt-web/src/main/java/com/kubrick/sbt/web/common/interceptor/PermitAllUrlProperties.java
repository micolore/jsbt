package com.kubrick.sbt.web.common.interceptor;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName PermitAllUrlProperties
 * @description: TODO
 * @date 2021/3/2 下午8:48
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.oauth2")
public class PermitAllUrlProperties {

    private List<String> ignoreUrls = new ArrayList<>();

    /**
     * 是否禁止嵌入iframe
     */
    private boolean iframeDeny = true;

}
