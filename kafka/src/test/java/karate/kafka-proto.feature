Feature: karate-kafka demo

  Background:
    * configure kafka =
      """
      {
        'bootstrap.servers': '127.0.0.1:29092'
      }
      """
    * register { name: 'hello-proto', path: 'classpath:karate/hello.proto', message: 'Hello' }

  Scenario:
    * def session = karate.consume('kafka')
    * session.count = 1
    * session.topic = 'test-topic'
    * session.schema = 'hello-proto'
    * session.start()

    * topic 'test-topic'
    * schema 'hello-proto'
    * key 'first'
    * value { message: 'hello', sender: { name: 'John Smith' } }
    * produce kafka

    * def response = session.collect()
    * match response[0].key == 'first'
    * match response[0].value == { message: 'hello', sender: { name: 'John Smith' } }