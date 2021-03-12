package com.kubrick.jsbt.rpc.tls.server;

import com.kubrick.jsbt.rpc.tls.proto.GreeterGrpc;
import com.kubrick.jsbt.rpc.tls.proto.HelloReply;
import com.kubrick.jsbt.rpc.tls.proto.HelloRequest;
import io.grpc.stub.StreamObserver;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GreeterImpl
 * @description: TODO
 * @date 2021/3/11 下午4:48
 */
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
