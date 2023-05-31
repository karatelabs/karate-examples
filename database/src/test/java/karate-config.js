function fn() {
  var DbUtils = Java.type('karate.DbUtils');
  var dbConfig = {
    url: 'jdbc:h2:mem:testdb',
    username: 'sa',
    password: '',
    driverClassName: 'org.h2.Driver'
  };
  var serverPort = karate.properties['server.port'];
  return {
    urlBase: 'http://localhost:' + serverPort,
    db: new DbUtils(dbConfig) 
  };
}
