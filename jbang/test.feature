Feature:

Background:
* def faker = new com.github.javafaker.Faker()

Scenario:
* def name = faker.name().fullName()

* url 'https://httpbin.org/post'
* request { name: '#(name)' }
* method post
* status 200
* match response.json.name == name
