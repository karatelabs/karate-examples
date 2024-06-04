Feature: example of how to use java interop in a karate test
    you can compare this code with HelloTest.java and see how
    karate compares one-to-one with the equivalent in java
    you can avoid the boilerplate in the background by using a well-designed
    utility / helper java class (recommended) refer: grpc.feature

Background:
* def HelloServer = Java.type('io.karatelabs.examples.grpc.HelloServer')
* def HelloClient = Java.type('io.karatelabs.examples.grpc.HelloClient')
* def server = HelloServer.start(0)
* def client = new HelloClient('localhost', server.getPort())

Scenario:
* def result = client.hello('world')
* match result == 'hello world'
* client.shutdown()
