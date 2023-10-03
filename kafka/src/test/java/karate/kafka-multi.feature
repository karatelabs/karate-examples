Feature:

  Background:
    * configure kafka =
    """
    {
      'bootstrap.servers': '127.0.0.1:29092',
      'schema.registry.url': 'http://localhost:8081'
    }
    """
    * register { name: 'complex', path: 'classpath:karate/complex.avsc' }

  Scenario: Complex Avro - test-topic1
    * def session = karate.consume('kafka')
    * session.topic = 'test-topic1'
    * session.count = 1
    * session.start()

    * topic 'test-topic1'
    * schema 'complex'
    * key 'zero'
    * headers { foo: 'bar1', baz: 'ban1' }
    * value
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
    * produce kafka

    * def response = session.collect()
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
    * def session = karate.consume('kafka')
    * session.topic = 'test-topic2'
    * session.count = 1
    * session.start()

    * topic 'test-topic2'
    * schema 'complex'
    * key 'zero'
    * headers { foo: 'bar1', baz: 'ban1' }
    * value
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
    * produce kafka

    * def response = session.collect()
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
