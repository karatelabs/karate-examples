package io.karatelabs.examples.grpc;

import io.grpc.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloServer extends HelloServiceGrpc.HelloServiceImplBase {

    static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public static class MetadataInterceptor implements ServerInterceptor {
        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
                ServerCall<ReqT, RespT> call,
                Metadata headers,
                ServerCallHandler<ReqT, RespT> next) {
            for (String key : headers.keys()) {
                if (!key.endsWith("-bin")) {
                    Metadata.Key<String> metaKey = Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER);
                    logger.debug("header {}: {}", key, headers.get(metaKey));
                }
            }
            ServerCall<ReqT, RespT> wrappedCall = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
                @Override
                public void sendHeaders(Metadata responseHeaders) {
                    Metadata.Key<String> authKey = Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER);
                    String authValue = headers.get(authKey);
                    if (authValue != null) {
                        responseHeaders.put(
                                Metadata.Key.of("authorization-response", Metadata.ASCII_STRING_MARSHALLER),
                                authValue + "-response"
                        );
                    }
                    super.sendHeaders(responseHeaders);
                }
            };
            return next.startCall(wrappedCall, headers);
        }
    }

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloReply> response) {
        // access metadata in the method using context
        String message = "hello " + request.getName();
        Metadata headers = MetadataServerInterceptor.METADATA_CONTEXT_KEY.get();
        if (headers != null) {
            Metadata.Key<String> authKey = Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER);
            String auth = headers.get(authKey);
            logger.debug("auth header in hello(): {}", auth);
            if (auth != null) {
                message = message + " with authorization: " + auth;
            }
        }
        HelloReply reply = HelloReply.newBuilder()
                .setMessage(message)
                .build();
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
        return new StreamObserver<>() {
            final List<String> names = new ArrayList<>();

            @Override
            public void onNext(HelloRequest helloRequest) {
                names.add(helloRequest.getName());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
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
        return new StreamObserver<>() {
            final List<String> names = new ArrayList<>();

            @Override
            public void onNext(HelloRequest helloRequest) {
                names.add(helloRequest.getName());
                responseObserver.onNext(HelloReply.newBuilder().setMessage("hello " + names).build());
            }

            @Override
            public void onError(Throwable throwable) {
                logger.warn("{}", throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    public static Server start(int port, boolean ssl) throws Exception {
        Server server;
        ServerBuilder<?> serverBuilder;
        if (ssl) {
            TlsServerCredentials.Builder tlsBuilder = TlsServerCredentials.newBuilder()
                    .keyManager(
                            new File("src/test/java/server.crt"),
                            new File("src/test/java/server.pem"));
            serverBuilder = Grpc.newServerBuilderForPort(port, tlsBuilder.build());
        } else {
            serverBuilder = ServerBuilder.forPort(port);
        }
        // add the interceptor for metadata handling
        server = serverBuilder
                .addService(new HelloServer())
                .intercept(new MetadataInterceptor())
                .intercept(new MetadataServerInterceptor()) // For context propagation
                .build();
        server.start();
        logger.debug("grpc server started on port: " + server.getPort());
        return server;
    }

    // helper to make metadata available via context
    public static class MetadataServerInterceptor implements ServerInterceptor {
        public static final Context.Key<Metadata> METADATA_CONTEXT_KEY = Context.key("metadata");

        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
                ServerCall<ReqT, RespT> call,
                Metadata headers,
                ServerCallHandler<ReqT, RespT> next) {
            Context context = Context.current().withValue(METADATA_CONTEXT_KEY, headers);
            return Contexts.interceptCall(context, call, headers, next);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = start(9555, false);
        server.awaitTermination();
    }

}