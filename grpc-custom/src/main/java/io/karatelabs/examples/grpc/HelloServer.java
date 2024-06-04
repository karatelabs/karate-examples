package io.karatelabs.examples.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloServer extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloReply> response) {
        HelloReply reply = HelloReply.newBuilder().setMessage("hello " + request.getName()).build();
        response.onNext(reply);
        response.onCompleted();
    }

    public static Server start(int port) throws Exception {
        Server server = ServerBuilder.forPort(port)
                .addService(new HelloServer())
                .build()
                .start();
        System.out.println("grpc server started on port: " + server.getPort());
        return server;
    }

}
