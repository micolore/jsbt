package com.kubrick.jsbt.sso.server.service.impl;

import com.kubrick.jsbt.sso.client.sso.client.rpc.Result;
import com.kubrick.jsbt.sso.server.model.App;
import com.kubrick.jsbt.sso.server.service.AppService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName AppServiceImpl
 * @description: TODO
 * @date 2021/2/7 下午5:29
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    private static List<App> appList;

    static {
        appList = new ArrayList<>();
        appList.add(new App("服务端1", "server1", "123456"));
        appList.add(new App("客户端1", "demo1", "123456"));
        appList.add(new App("客户端2", "demo2", "123456"));
    }

    @Override
    public boolean exists(String appId) {
        return appList.stream().anyMatch(app -> app.getAppId().equals(appId));
    }

    @Override
    public Result<Void> validate(String appId, String appSecret) {
        for (App app : appList) {
            if (app.getAppId().equals(appId)) {
                if (app.getAppSecret().equals(appSecret)) {
                    return Result.success();
                }
                else {
                    return Result.createError("appSecret有误");
                }
            }
        }
        return Result.createError("appId不存在");
    }
}

