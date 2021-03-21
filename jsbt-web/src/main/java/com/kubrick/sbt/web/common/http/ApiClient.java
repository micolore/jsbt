package com.kubrick.sbt.web.common.http;

import com.dtflys.forest.annotation.Get;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AmapClient
 * @description:
 * 1、不要注入到controller层里面
 * @date 2021/1/9 下午11:46
 */
public interface ApiClient {

    @Get("http://baidu.com")
    String getBaidu();

}
