# Karate and Databases

This is a "standard" [Spring Boot](https://spring.io/projects/spring-boot) project. A few highlights:

* shows how to run Karate via JUnit using the [`@SpringBootTest`](https://spring.io/guides/gs/testing-web) annotation
* uses an `@Autowired` `ServletWebServerApplicationContext` to get the (dynamic) port that the Spring Boot application is running on
  * in test mode, `server.port=0` in [src/test/java/application.properties](src/test/java/application.properties) configures the server to start on a random and unused port
  * for more options, refer to [this article at Baeldung](https://www.baeldung.com/spring-boot-running-port).
* the Karate test [`dogs.feature`](src/test/java/karate/dogs.feature) is a "hybrid" test that calls the REST API as well as the database directly via JDBC
* shows how [Java interop](https://github.com/karatelabs/karate#calling-java) can be used in [karate-config.js](src/test/java/karate-config.js) to configure as well as initialize the database connection
  * note how the port number is dynamically passed to Karate from the Spring context in [`DogsTest.java`](src/test/java/karate/DogsTest.java)

The Java "glue" code that connects to the database and is able to make SQL queries is in [`DbUtils.java`](src/test/java/karate/DbUtils.java). Note that this is just a starting point. You can use this code as a reference and implement what is appropriate for your project. This example uses the [Spring JDBC template](https://spring.io/guides/gs/relational-data-access) that makes working with databases a lot easier in Java. You are free to use any Java code that gets the job done. Calling Java code from Karate is easy using [Java interop](https://github.com/karatelabs/karate#calling-java).

Note that this code just needs to be created "one time". Once it is in place, no more Java code needs to be written (or compiled). Karate tests can be written by anyone in the team including non-programmers. And the tests will remain clean and focused on the business-scenario.

## Running

Run `mvn test` to execute the JUnit test: [`DogsTest.java`](src/test/java/karate/DogsTest.java)

## Using the Shaded JAR

In some projects, when Spring or Spring dependencies use the same libraries that Karate uses, you may run into classloading or issues such as a "class not found exception". You can switch to using the shaded "fat jar" of `karate-core` as [explained here](https://github.com/karatelabs/karate#karate-core-fat-jar).

If you use the shaded JAR, you may see extra logging from libraries such as Thymeleaf. Instead of the `org.thymeleaf` entry in [`logback-test.xml`](src/test/java/logback-test.xml) you may need to change it to `karate.org.thymeleaf`.

## Further Reading
* [Karate and Databases on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+database)
* [Karate and CLI](../cli/README.md) - teams have reported success with using Karate to call CLI commands that in turn call the 
database
* [Karate and Spring Boot](../spring-boot/README.md)
* [Karate and SSL](../ssl/README.md) - another minimal Spring Boot Maven project.



