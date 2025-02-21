Feature: ssl with trust store / cert

  Background:
    * configure ssl =
      """
      {
        trustStore: 'classpath:server-keystore.p12',
        trustStorePassword: 'karate-mock',
        trustStoreType: 'pkcs12'
      }
      """
    * url 'https://localhost:8080'

  Scenario:
    * path 'test'
    * method get
    * status 200
    * match response == { success: true }
