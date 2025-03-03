Feature:

Background:
* configure kafka =
"""
{ 
  'bootstrap.servers': 'localhost:29093',
  'security.protocol': 'SSL',
  'ssl.truststore.location': 'ssl/client.truststore.pkcs12',
  'ssl.truststore.password': 'karate'
}
"""

Scenario:
# set up the listener before triggering any kafka events
* def channel = karate.channel('kafka')

* def consumer = channel.consumer()

* consumer.topic = 'test-topic'
* consumer.count = 1
* consumer.start()

# this api call will trigger a kafka message (from mock.feature)
* url 'http://localhost:' + karate.properties['server.port']
* method get
* match response == { success: true }

# here we wait until the session gets the expected message
* def response = consumer.pop()
* match response.key == 'first'
* match response.value == { hello: 'world' }
