# Karate and Databases

This is a "standard" Spring Boot project. A few highlights:

* shows how to run Karate via JUnit using the [`@SpringBootTest`](https://spring.io/guides/gs/testing-web) annotation
* uses an `@Autowired` `ServletWebServerApplicationContext` to get the (dynamic) port that the Spring Boot application is running on
  * for more options, refer to [this article at Baeldung](https://www.baeldung.com/spring-boot-running-port).
* the Karate test [`dogs.feature`](src/test/java/karate/dogs.feature) is a "hybrid" test that calls the REST API as well as the database directly via JDBC
* shows how [Java interop](https://github.com/karatelabs/karate#calling-java) can be used in [karate-config.js](src/test/java/karate-config.js) to configure as well as initialize the database connection
  * note how the port number is dynamically passed to Karate from the Spring context in [`DogsTest.java`](src/test/java/karate/DogsTest.java)

The Java "glue" code that connects to the database and is able to make SQL queries is in [`DbUtils.java`](src/test/java/karate/DbUtils.java). Note that this is just a starting point. You can use this code as a reference and implement what is appropriate for your project. 

Note that this code just needs to be created "one time". Once it is in place, no more Java code needs to be written (or compiled). Karate tests can be written by anyone in the team including non-programmers. And the tests will remain clean and focused on the business-scenario.

## Running
Run `mvn test` to execute the JUnit test: [`DogsTest.java`](src/test/java/karate/DogsTest.java)

## Further Reading
* [Karate and Databases on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+database)



