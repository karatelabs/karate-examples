Feature: micronaut integration test

Background:
* url urlBase

Scenario: hello endpoint
* path 'hello'
* method get
* status 200
* match response == 'hello world'
