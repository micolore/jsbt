package com.kubrick.jsbt.rpc.tls.client;

import com.kubrick.jsbt.rpc.tls.proto.GreeterGrpc;
import com.kubrick.jsbt.rpc.tls.proto.HelloReply;
import com.kubrick.jsbt.rpc.tls.proto.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author k
 * @version 1.0.0
 * @ClassName HelloWorldClient
 * @description: TODO
 * @date 2021/3/11 下午4:47
 */
public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient() throws SSLException {

        this(NettyChannelBuilder.forAddress("localhost", 50052)
                .negotiationType(NegotiationType.TLS)
                .sslContext(buildSslContext())
                .build());
    }

    HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    private static SslContext buildSslContext() throws SSLException {
        String p = "/Users/kubrick/Documents/workspace/java/study/temp/grpc-mutual-tls-demo/tls";
        String clientCertChainFilePath = p + "/client.crt";
        String clientPrivateKeyFilePath = p + "/client.pem";
        String trustCertCollectionFilePath = p + "/ca.crt";

        ClassLoader classLoader = HelloWorldClient.class.getClassLoader();

        SslContextBuilder builder = GrpcSslContexts.forClient();
        builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
        builder.trustManager(new File(trustCertCollectionFilePath));

        return builder.build();
    }

    public static void main(String[] args) throws Exception {

        HelloWorldClient client = new HelloWorldClient();

        try {
            String user = "123";
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1; i++) {
                client.greet(user);
            }
            long end = System.currentTimeMillis();
            System.out.println("10000 times costs " + (end - start));
        } finally {
            client.shutdown();
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     */
    public void greet(String name) {
//        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
//        logger.info("Greeting: " + response.getMessage());
    }
}
