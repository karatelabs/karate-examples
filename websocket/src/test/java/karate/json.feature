Feature: demo auto-conversion to json

  Scenario:
    * def session = karate.channel('websocket')
    * session.url = 'wss://ws.postman-echo.com/raw'
    * def Adapter = Java.type('io.karatelabs.websocket.JsonAdapter')
    * session.adapter = new Adapter()
    * session.start()

    * session.send({ message: 'hello' })

    * def response = session.collect()
    * match response == [{ message: 'hello' }]
