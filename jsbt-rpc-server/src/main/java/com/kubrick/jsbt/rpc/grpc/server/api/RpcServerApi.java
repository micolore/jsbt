package com.kubrick.jsbt.rpc.grpc.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RpcServerApi
 * @description: TODO
 * @date 2021/3/10 下午9:00
 */
@RestController
@RequiredArgsConstructor
public class RpcServerApi {


    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "grpc server") String name) {
        return "server ok";
    }
}


