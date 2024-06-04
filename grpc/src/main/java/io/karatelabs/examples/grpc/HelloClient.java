package io.karatelabs.examples.grpc;

import io.grpc.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class HelloClient {

    static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

    private final ManagedChannel channel;
    private final HelloServiceGrpc.HelloServiceStub stub;

    HelloClient(ManagedChannel channel) {
        this.channel = channel;
        stub = HelloServiceGrpc.newStub(channel);
    }

    public HelloClient(int port, boolean ssl) {
        this(getChannel(port, ssl));
    }

    private static ManagedChannel getChannel(int port, boolean ssl) {
        String host = "localhost";
        try {
            if (ssl) {
                ChannelCredentials creds = TlsChannelCredentials.newBuilder()
                        .keyManager(
                                new File("src/test/java/client.crt"),
                                new File("src/test/java/client.pem"))
                        .trustManager(new File("src/test/java/ca.crt"))
                        .build();
                return Grpc.newChannelBuilderForAddress(host, port, creds)
                        .build();
            } else {
                return ManagedChannelBuilder.forAddress(host, port)
                        .usePlaintext()
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String hello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        CompletableFuture<String> result = new CompletableFuture<>();
        stub.hello(request, new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply helloReply) {
                result.complete(helloReply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        try {
            return result.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> lotsOfReplies(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        CompletableFuture<List<String>> result = new CompletableFuture<>();
        stub.lotsOfReplies(request, new StreamObserver<HelloReply>() {
            List<String> messages = new ArrayList<>();

            @Override
            public void onNext(HelloReply helloReply) {
                messages.add(helloReply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                result.complete(messages);
            }
        });
        try {
            return result.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String lotsOfGreetings(String... names) {
        CompletableFuture<String> result = new CompletableFuture<>();
        StreamObserver<HelloRequest> requestObserver = stub.lotsOfGreetings(new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply helloReply) {
                result.complete(helloReply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        requestObserver.onNext(HelloRequest.newBuilder().setName(names[0]).build());
        requestObserver.onNext(HelloRequest.newBuilder().setName(names[1]).build());
        requestObserver.onNext(HelloRequest.newBuilder().setName(names[2]).build());
        requestObserver.onCompleted();
        try {
            return result.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> bidiHello(String... names) {
        CompletableFuture<List<String>> result = new CompletableFuture<>();
        StreamObserver<HelloRequest> requestObserver = stub.bidiHello(new StreamObserver<HelloReply>() {
            List<String> messages = new ArrayList<>();

            @Override
            public void onNext(HelloReply helloReply) {
                messages.add(helloReply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                result.complete(messages);
            }
        });
        requestObserver.onNext(HelloRequest.newBuilder().setName(names[0]).build());
        requestObserver.onNext(HelloRequest.newBuilder().setName(names[1]).build());
        requestObserver.onNext(HelloRequest.newBuilder().setName(names[2]).build());
        requestObserver.onCompleted();
        try {
            return result.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        HelloClient client = new HelloClient(9555, false);
        String response = client.hello("foo");
        logger.debug("response: {}", response);
        client.shutdown();
    }

}
