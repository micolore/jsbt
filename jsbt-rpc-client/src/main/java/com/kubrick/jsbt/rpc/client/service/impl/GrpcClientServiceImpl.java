package com.kubrick.jsbt.rpc.client.service.impl;

import com.kubrick.jsbt.rpc.client.service.IGrpcClientService;
import com.kubrick.jsbt.rpc.lib.GreeterGrpc;
import com.kubrick.jsbt.rpc.lib.GreeterOuterClass;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GrpcClientServiceImpl
 * @description: TODO
 * @date 2021/2/22 下午8:18
 */
@Service
public class GrpcClientServiceImpl implements IGrpcClientService {

    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    /**
     * 通过本地存protocol buffer存根序列化后调用gRPC服务端
     *
     * @param name
     * @return
     */
    @Override
    public String sendMessage(String name) {
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(serverChannel);
        GreeterOuterClass.HelloReply response = stub.sayHello(GreeterOuterClass.HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }
}
