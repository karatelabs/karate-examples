Feature: karate-kafka demo

Background:
* configure kafka =
"""
{ 
  'bootstrap.servers': '127.0.0.1:29092',
  'schema.registry.url': 'http://localhost:8081'
}
"""

* register { name: 'hello', path: 'classpath:karate/hello.avsc' }

Scenario:
* def session = karate.consume('kafka')
* session.topic = 'test-topic'
* session.count = 2
* session.filter = x => x.key != 'zero'
* session.start()

* topic 'test-topic'
* schema 'hello'
* key 'zero'
* value { message: 'hello0', info: { first: 0, second: false } }
* produce kafka

* topic 'test-topic'
* schema 'hello'
* headers { foo: 'bar1', baz: 'ban1' }
* key 'first'
* value { message: 'hello1', info: { first: 1, second: true } }
* produce kafka

* topic 'test-topic'
* schema 'hello'
* key 'second'
* value { message: 'hello2', info: { first: 2, second: false } }
* produce kafka

* def response = session.collect()
* match response[0].key == 'first'
* match response[0].headers == { foo: 'bar1', baz: 'ban1' }
* match response[1].value == { message: 'hello2', info: { first: 2, second: false } }