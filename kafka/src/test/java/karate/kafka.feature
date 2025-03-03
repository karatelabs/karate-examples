Feature: karate-kafka demo

Background:
* configure kafka =
"""
{ 
  'bootstrap.servers': '127.0.0.1:29092',
  'schema.registry.url': 'http://localhost:8081'
}
"""

Scenario:
* def channel = karate.channel('kafka')
* channel.register({ name: 'hello', path: 'classpath:karate/hello.avsc' })

* def consumer = channel.consumer()

* consumer.topic = 'test-topic'
* consumer.count = 2
* consumer.filter = x => x.key != 'zero'
* consumer.timeout = 5000
* consumer.start()

* def producer = channel.producer()

* producer.topic = 'test-topic'
* producer.schema = 'hello'
* producer.key = 'zero'
* producer.value = { message: 'hello0', info: { first: 0, second: false } }
* producer.send()

* producer.headers = { foo: 'bar1', baz: 'ban1' }
* producer.key = 'first'
* producer.value = { message: 'hello1', info: { first: 1, second: true } }
* producer.send()

* producer.key = 'second'
* producer.headers = null
* producer.value = { message: 'hello2', info: { first: 2, second: false } }
* producer.send()

* def response = consumer.collect()
* match response[0].key == 'first'
* match response[0].headers == { foo: 'bar1', baz: 'ban1' }
* match response[1].value == { message: 'hello2', info: { first: 2, second: false } }