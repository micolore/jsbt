package com.kubrick.jsbt.sso.server.service;

import com.kubrick.jsbt.sso.client.sso.client.rpc.Result;

public interface AppService {

    boolean exists(String appId);

    Result<Void> validate(String appId, String appSecret);
}
