# Karate and gRPC

Karate has native support for gRPC as an optional dependency (non-open source and commercial). Enterprise users can find more information here: [Karate-gRPC](https://github.com/karatelabs/karate-addons/tree/main/karate-grpc). 

A license is required for running (for e.g. in CI/CD) and a Karate Labs IDE subscription is required per developer seat. Make sure you have a `.karate/karate.lic` file in place before running the example.

## Running
This is a normal Java / Maven project so running `mvn clean compile test` will be sufficient to run all the tests.

Take a look at [`hello.feature`](src/test/java/karate/hello.feature) to see how simple yet expressive gRPC tests can be.

## Further Reading
* [grpc-custom](../grpc-custom/README.md) - it is possible to test gRPC by writing the Java integration code yourself and generating Java code, but the approach above is recommended

