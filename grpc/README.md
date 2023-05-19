# Karate and gRPC

This sample project demonstrates how you can test gRPC if your implementation is in Java. It also demonstrates concepts you can use for any case where you use Java interop to connect Karate to any custom code, library or communication protocol.

When you use well designed utility classes, your Karate tests will be clean and only focus on making a call and what data is sent and received.

For example if you look at [`grpc.feature`](src/test/java/karate/grpc.feature) the test is just one line. Behind the scenes a gRPC server and client is initialized and the call is made. The HTML report even includes details of the call and the messages on the wire, which you can easily customize.

Rather than attempt a generic and dynamic approach, this approach shown here can re-use your existing gRPC implementation code-base and domain classes. If your gRPC implementation is in another language, you can still use this approach, but use Maven (or Gradle) to generate the Java code from the `.proto` files.

