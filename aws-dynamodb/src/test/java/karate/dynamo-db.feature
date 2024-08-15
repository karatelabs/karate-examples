Feature:

  Scenario:
    * def Utils = Java.type('examples.DynamoDbUtils')
    * def db = new Utils()
    * def result = db.getItem('email#ptrthomas@gmail.com')
    * match result == { myFieldName: '#string' }