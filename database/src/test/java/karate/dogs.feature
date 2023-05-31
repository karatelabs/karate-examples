Feature: dogs end-point that uses jdbc as part of the test

Background:
* url urlBase

Scenario: create and retrieve a dog

# create a dog
* path 'dogs'
* request { name: 'Scooby' }
* method post
* status 200
* match response == { id: '#number', name: 'Scooby' }

* def id = response.id

# get by id
* path 'dogs', id
* method get
* status 200
* match response == { id: '#(id)', name: 'Scooby' }

# get all dogs
* path 'dogs'
* method get
* status 200
* match response contains { id: '#(id)', name: 'Scooby' }

# use jdbc to validate
# look at karate-config.js to see how "db" was initialized

# since the DbUtils returns a Java List (of Map-s), it becomes normal JSON here !
# which means that you can use the full power of Karate's 'match' syntax
* def dogs = db.readRows('SELECT * FROM DOGS')
* match dogs contains { ID: '#(id)', NAME: 'Scooby' }

* def dog = db.readRow('SELECT * FROM DOGS D WHERE D.ID = ' + id)
* match dog.NAME == 'Scooby'

* def test = db.readValue('SELECT ID FROM DOGS D WHERE D.ID = ' + id)
* match test == id
