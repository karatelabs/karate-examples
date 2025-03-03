Feature: karate-kafka demo

Background:
* configure kafka =
"""
{ 
  'bootstrap.servers': '127.0.0.1:29092'
}
"""
* def channel = karate.channel('kafka')
* channel.register({ name: 'hello-proto', path: 'classpath:karate/hello.proto', message: 'Hello', roots: ['classpath:karate'] })

Scenario:
* def consumer = channel.consumer()
* consumer.count = 1
* consumer.topic = 'test-topic'
* consumer.schema = 'hello-proto'
* consumer.start()

* def producer = channel.producer()

* producer.topic = 'test-topic'
* producer.schema = 'hello-proto'
* producer.key = 'first'
* producer.value = { message: 'hello', sender: { name: 'John Smith' } }
* producer.send()

* def response = consumer.collect()
* match response[0].key == 'first'
* match response[0].value == { message: 'hello', sender: { name: 'John Smith' } }