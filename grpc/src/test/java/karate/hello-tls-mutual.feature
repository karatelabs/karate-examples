Feature: grpc with tls / ssl and using client-side certificate (mutual) auth

  Background:
    * def session = karate.consume('grpc')
    * session.host = 'localhost'
    * session.port = karate.properties['grpc.port']

    * session.config = { trustCert: 'classpath:ca.crt', clientCert: 'classpath:client.crt', clientKey: 'classpath:client.pem' }

    # note that session.config can be updated multiple times
    * def method = 'Hello'
    # and that everything is pure-JS which means Karate variable references work directly
    * session.config = { proto: 'classpath:karate/hello.proto', service: 'HelloService', method: method }

  Scenario: unary
    * session.send({ name: 'John' })
    * match session.pop() == { message: 'hello John' }
    * session.send({ name: 'Smith' })
    * match session.pop() == { message: 'hello Smith' }