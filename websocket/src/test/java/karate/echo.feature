Feature: demo plain-text

  Scenario:
    * def session = karate.channel('websocket')
    * session.url = 'wss://ws.postman-echo.com/raw'
    * session.start()

    * session.send('hello')

    * def response = session.collect()
    * match response == ['hello']
