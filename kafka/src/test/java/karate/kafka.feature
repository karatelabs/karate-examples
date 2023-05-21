Feature: example of using helper classes so that your karate tests
    can focus only on the calls, requests and responses
    look at karate-config.js for how "Kafka" was initialized

Scenario:
* Kafka.send('hello world')
* def result = Kafka.listen()
* match result == ['hello world']
