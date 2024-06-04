Feature: grpc

  Background:
    * def session = karate.consume('grpc')
    * session.host = 'localhost'
    * session.port = karate.properties['grpc.port']
    * session.proto = 'classpath:karate/hello.proto'
    * session.service = 'HelloService'

  Scenario: unary
    * session.method = 'Hello'
    * session.send({ name: 'John' })
    * match session.pop() == { message: 'hello John' }
    * session.send({ name: 'Smith' })
    * match session.pop() == { message: 'hello Smith' }

  Scenario: server stream
    * session.method = 'LotsOfReplies'
    * session.count = 3
    * session.send({ name: 'John' })
    * def result = session.collect()
    * match result ==
      """
      [
        { message: 'hello John 1' },
        { message: 'hello John 2' },
        { message: 'hello John 3' }
      ]
      """

  Scenario: client stream
    * session.method = 'LotsOfGreetings'
    * session.stream = true
    * session.send({ name: 'John' })
    * session.send({ name: 'Smith' })
    * session.send({ name: 'Jane' })
    * session.flush()
    * match session.pop() == { message: 'hello [John, Smith, Jane]' }

  Scenario: bidi
    * session.method = 'BidiHello'
    * session.stream = true
    * session.count = 3
    * session.send({ name: 'John' })
    * session.send({ name: 'Smith' })
    * session.send({ name: 'Jane' })
    * session.flush()
    * match session.collect() ==
      """
      [
        { message: 'hello [John]' },
        { message: 'hello [John, Smith]' },
        { message: 'hello [John, Smith, Jane]' }
      ]
      """
