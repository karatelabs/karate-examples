Feature: karate-kafka demo

Background:
* configure kafka =
"""
{ 
  'bootstrap.servers': '127.0.0.1:29092'
}
"""

Scenario:
* def channel = karate.channel('kafka')
* def consumer = channel.consumer()

* consumer.count = 1
* consumer.topic = 'test-topic'
* consumer.timeout = 5000
* consumer.start()

* def producer = channel.producer()

* producer.topic = 'test-topic'
* producer.key = 'first'
* producer.value = { message: 'hello', info: { first: 1, second: true } }
* producer.send()

* def response = consumer.pop()
* match response.key == 'first'
* match response.value == { message: 'hello', info: { first: 1, second: true } }