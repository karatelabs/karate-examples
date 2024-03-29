Feature: example of using helper classes so that your karate tests
    can focus only on the calls, requests and responses
    look at karate-config.js for how "kafka" was initialized

Scenario:
* kafka.send({ message: 'hello', info: { first: 5, second: true } })
* def result = kafka.listen()
* match result == [{ message: 'hello', info: { first: 5, second: true } }]
