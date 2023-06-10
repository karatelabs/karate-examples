Feature: example of using helper classes so that your karate tests
    can focus only on the calls, requests and responses
    look at karate-config.js for how "rmq" was initialized

Scenario:
* rmq.send('hello world')
* def result = rmq.listen()
* match result == ['hello world']
