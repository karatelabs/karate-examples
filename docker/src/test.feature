Feature: sample karate api test script

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  Scenario: get all users and then get the first user by id
    * path 'users'
    * method get
    * status 200
    * match response[0] contains { id: 1, name: 'Leanne Graham', website: 'hildegard.org' }
    
    * def first = response[0]

    * path 'users', first.id
    * method get
    * status 200
    * match response == first