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
* def session = karate.consume('kafka')
* session.topic = 'test-topic'
* session.count = 1
* session.start()

# this api call will trigger a kafka message (from mock.feature)
* url 'http://localhost:' + karate.properties['server.port']
* method get
* match response == { success: true }

# here we wait until the session gets the expected message
* def response = session.collect()
* match response[0].key == 'first'
* match response[0].value == { hello: 'world' }
