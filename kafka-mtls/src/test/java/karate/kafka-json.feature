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
* def channel = karate.channel('kafka')

* def consumer = channel.consumer()
* consumer.topic = 'test-topic'
* consumer.count = 1
* consumer.start()

* def producer = channel.producer()

* producer.topic = 'test-topic'
* producer.key = 'first'
* producer.value = { message: 'hello', info: { first: 1, second: true } }
* producer.send()

* def response = consumer.pop()
* match response.key == 'first'
* match response.value == { message: 'hello', info: { first: 1, second: true } }