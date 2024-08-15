Feature:

  Scenario:
    * def Utils = Java.type('examples.DynamoDbUtils')
    * def db = new Utils()
    * def result = db.getItem('myKeyValue')
    * match result == { myFieldName: '#string' }