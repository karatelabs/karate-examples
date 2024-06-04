package io.karatelabs.examples.grpc;

import io.grpc.Grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.TlsServerCredentials;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloServer extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloReply> response) {
        HelloReply reply = HelloReply.newBuilder().setMessage("hello " + request.getName()).build();
        response.onNext(reply);
        response.onCompleted();
    }

    @Override
    public void lotsOfReplies(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(HelloReply.newBuilder().setMessage("hello " + name + " 1").build());
        responseObserver.onNext(HelloReply.newBuilder().setMessage("hello " + name + " 2").build());
        responseObserver.onNext(HelloReply.newBuilder().setMessage("hello " + name + " 3").build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloRequest> lotsOfGreetings(StreamObserver<HelloReply> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            List<String> names = new ArrayList<>();

            @Override
            public void onNext(HelloRequest helloRequest) {
                names.add(helloRequest.getName());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(HelloReply.newBuilder().setMessage("hello " + names).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest> bidiHello(StreamObserver<HelloReply> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            List<String> names = new ArrayList<>();

            @Override
            public void onNext(HelloRequest helloRequest) {
                names.add(helloRequest.getName());
                responseObserver.onNext(HelloReply.newBuilder().setMessage("hello " + names).build());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    public static Server start(int port, boolean ssl) throws Exception {
        Server server;
        if (ssl) {
            TlsServerCredentials.Builder tlsBuilder = TlsServerCredentials.newBuilder()
                    .keyManager(
                            new File("src/test/java/server.crt"),
                            new File("src/test/java/server.pem"));
            server = Grpc.newServerBuilderForPort(port, tlsBuilder.build())
                    .addService(new HelloServer())
                    .build();
        } else {
            server = ServerBuilder.forPort(port)
                    .addService(new HelloServer())
                    .build();
        }
        server.start();
        System.out.println("grpc server started on port: " + server.getPort());
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = start(9555, false);
        server.awaitTermination();
    }

}
