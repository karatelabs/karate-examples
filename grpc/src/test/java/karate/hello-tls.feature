Feature: grpc with tls / ssl

  Background:
    * def session = karate.consume('grpc')
    * session.host = 'localhost'
    * session.port = karate.properties['grpc.port']

    # note this short-cut to set multiple session properties on one line
    * session.config = { proto: 'classpath:karate/hello.proto', service: 'HelloService', method: 'Hello' }

    * session.trustCert = 'classpath:ca.crt'

  Scenario: unary
    * session.send({ name: 'John' })
    * match session.pop() == { message: 'hello John' }
    * session.send({ name: 'Smith' })
    * match session.pop() == { message: 'hello Smith' }