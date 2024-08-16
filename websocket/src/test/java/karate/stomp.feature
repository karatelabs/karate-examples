Feature: tests the following spring-websocket example
  https://github.com/spring-guides/gs-messaging-stomp-websocket

  Background:
    * def result =
      """
      function(name) {
        var session = karate.get('session');
        var message = { command: 'SEND', headers: { destination: '/app/hello' }, body: { name: name } };
        session.send(message);
        var response = session.pop();
        return response.body.content;
      }
      """

  Scenario:
    * def session = karate.channel('websocket')
    * session.url = 'ws://localhost:8080/gs-guide-websocket'
    * def Adapter = Java.type('karate.StompAdapter')
    * session.adapter = new Adapter()
    * session.start()
    * session.send({ command: 'CONNECT', headers: { 'accept-version': '1.2', 'heart-beat': '0,0' } })
    * match session.pop() contains { command: 'CONNECTED' }
    * session.send({ command: 'SUBSCRIBE', headers: { id: 'sub-0', destination: '/topic/greetings' } })

    * match result('foo') == 'Hello, foo!'
    * match result('bar') == 'Hello, bar!'

    * session.send({ command: 'DISCONNECT', headers: { receipt: 'disc-0' } })
    * match session.pop() contains { command: 'RECEIPT', headers: { 'receipt-id': 'disc-0' } }
    * session.stop()
