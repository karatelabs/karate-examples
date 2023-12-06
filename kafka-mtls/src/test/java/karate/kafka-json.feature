Feature: karate-kafka demo

Background:
* configure kafka =
"""
{ 
  'bootstrap.servers': 'localhost:29093',
  'security.protocol': 'SSL',
  'ssl.truststore.location': 'ssl/client.truststore.pkcs12',
  'ssl.truststore.password': 'karate'
}
"""

Scenario:
* def session = karate.consume('kafka')
* session.topic = 'test-topic'
* session.count = 1
* session.start()

* topic 'test-topic'
* key 'first'
* value { message: 'hello', info: { first: 1, second: true } }
* produce kafka

* def response = session.collect()
* match response[0].key == 'first'
* match response[0].value == { message: 'hello', info: { first: 1, second: true } }