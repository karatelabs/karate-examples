Feature:

  Background:
    * configure kafka =
    """
    {
      'bootstrap.servers': '127.0.0.1:29092',
      'schema.registry.url': 'http://localhost:8081'
    }
    """
    * def channel = karate.channel('kafka')
    * channel.register({ name: 'complex', path: 'classpath:karate/complex.avsc' })

  Scenario: Complex Avro - test-topic1
    * def consumer = channel.consumer()

    * consumer.topic = 'test-topic1'
    * consumer.count = 1
    * consumer.start()

    * def producer = channel.producer()

    * producer.topic = 'test-topic1'
    * producer.schema = 'complex'
    * producer.key = 'zero'
    * producer.headers = { foo: 'bar1', baz: 'ban1' }
    * def value =
    """
    {
        "meta": {
            "metaId": "123",
            "metaType": "AAA",
            "metaChildren": [{ "name": "foo", "status": "ONE" }]
        },
        "payload": {
            "payloadId": "456",
            "payloadType": null,
            "payloadEnum": "FIRST",
            "payloadChild": {"field1": "foo", "field2": "bar"}
        }
    }
    """
    * producer.value = value
    * producer.send()

    * def response = consumer.collect()
    * match response[0].key == 'zero'
    * match response[0].headers == { foo: 'bar1', baz: 'ban1' }
    * match response[0].value ==
    """
    {
      "meta": {
        "metaId": "123",
        "metaType": "AAA",
        "metaChildren": [
          {
            "name": "foo",
            "status":"ONE"
          }
        ]
      },
      "payload": {
        "payloadId": "456",
        "payloadType": null,
        "payloadEnum": "FIRST",
        "payloadChild": {
          "field1": "foo",
          "field2": "bar"
        }
      }
    }
    """

  Scenario: Complex Avro - test-topic2
    * def consumer = channel.consumer()
    * consumer.topic = 'test-topic2'
    * consumer.count = 1
    * consumer.start()

    * def producer = channel.producer()

    * producer.topic = 'test-topic2'
    * producer.schema = 'complex'
    * producer.key = 'zero'
    * producer.headers = { foo: 'bar1', baz: 'ban1' }
    * def value =
    """
    {
        "meta": {
            "metaId": "123",
            "metaType": "AAA",
            "metaChildren": [{ "name": "foo", "status": "ONE" }]
        },
        "payload": {
            "payloadId": "456",
            "payloadType": null,
            "payloadEnum": "FIRST",
            "payloadChild": {"field1": "foo", "field2": "bar"}
        }
    }
    """
    * producer.value = value
    * producer.send()

    * def response = consumer.collect()
    * match response[0].key == 'zero'
    * match response[0].headers == { foo: 'bar1', baz: 'ban1' }
    * match response[0].value ==
    """
    {
      "meta": {
        "metaId": "123",
        "metaType": "AAA",
        "metaChildren": [
          {
            "name": "foo",
            "status":"ONE"
          }
        ]
      },
      "payload": {
        "payloadId": "456",
        "payloadType": null,
        "payloadEnum": "FIRST",
        "payloadChild": {
          "field1": "foo",
          "field2": "bar"
        }
      }
    }
    """
