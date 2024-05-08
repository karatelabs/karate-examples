Feature: Example of NPM usage of Java library in Karate feature file.

    Background:
    * def faker = new com.github.javafaker.Faker()
    
    Scenario:
    * def name = faker.name().fullName()
    
    Given url 'https://httpbin.org/post'
    And request { name: '#(name)' }
    When method post
    Then status 200
    And match response.json.name == name
