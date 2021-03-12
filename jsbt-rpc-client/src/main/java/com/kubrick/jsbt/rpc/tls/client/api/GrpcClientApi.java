package com.kubrick.jsbt.rpc.tls.client.api;


import com.kubrick.jsbt.rpc.tls.client.service.IGrpcClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GrpcClientApi
 * @description: grpc client api
 * @date 2021/2/22 下午8:17
 */
@RestController
@RequiredArgsConstructor
public class GrpcClientApi {

    private final IGrpcClientService grpcClientService;

    /**
     * Testing
     *
     * @param name
     * @return
     */
    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "ipman") String name) {
        return grpcClientService.sendMessage(name);
    }
}
