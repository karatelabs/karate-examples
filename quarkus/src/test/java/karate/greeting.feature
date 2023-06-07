Feature: quarkus integration test

Background:
* url urlBase

Scenario: hello endpoint
* path 'hello'
* method get
* status 200
* match response == 'hello'

Scenario: greeting endpoint
* path 'hello', 'greeting', 'world'
* method get
* status 200
* match response == 'hello world'