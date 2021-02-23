package com.kubrick.jsbt.rpc.client.test;

import com.kubrick.jsbt.rpc.lib.GreeterGrpc;
import com.kubrick.jsbt.rpc.lib.GreeterOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GrpcServiceClientTest
 * @description: TODO
 * @date 2021/2/23 下午8:01
 */
@Slf4j
public class GrpcServiceClientTest {
    String GRPC_SERVER_URL="127.0.0.1";

    private ManagedChannel channel;
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    @Test
    public void test() {
        try {
            GreeterOuterClass.HelloReply response = greeterBlockingStub.sayHello(GreeterOuterClass.HelloRequest.newBuilder().setName("grpc").build());
            log.info("接收到服务端返回结果:{}", response.toString());
        } finally {
            shutdown();
        }

    }

    @Before
    public void init() {
        log.info(" grpc-client connect start.");
        channel = ManagedChannelBuilder.forAddress(GRPC_SERVER_URL, 8800)
                .usePlaintext()
                .build();//池化处理 成本高
        greeterBlockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
