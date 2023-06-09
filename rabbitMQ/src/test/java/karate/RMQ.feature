Feature: example of using helper classes so that your karate tests
    can focus only on the calls, requests and responses
    look at karate-config.js for how "kafka" was initialized

Scenario:
* RMQUtils.send('hello world')
* def result = RMQUtils.listen()
* match result == ['hello world']
    * print 'Assertion pass'
