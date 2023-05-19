Feature: example of using helper classes so that your karate tests
    can focus only on the calls, requests and responses
    look at karate-config.js for how "Grpc" was initialized

Scenario:
* match Grpc.hello('world') == 'hello world'
