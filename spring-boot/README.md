# Karate and Spring Boot

Since Karate fits well into Maven and Gradle projects, it is easy to run Karate tests as part of a Spring Boot project. You can start and stop the main application from a JUnit class and then run your Karate test-suite.

* [examples/database](../database/README.md) - this is a "standard" Spring Boot project that shows how to combine Karate with the `@SpringBootTest` annotation to test a REST service as well as a database via JDBC
* [examples/ssl](../ssl/README.md) - another minimal Spring Boot project that shows how to test SSL / MTLS using client-side certificates configured.
* [karate-demo](https://github.com/karatelabs/karate/tree/master/karate-demo) - this is part of the main Karate open-source project, and has examples of how to use Karate to test a Spring Boot application
* [consumer-driven-contracts](https://github.com/karatelabs/karate/tree/master/examples/consumer-driven-contracts) - this is a multi-module Maven project that is focused on testing a Spring Boot application
* [hello-karate](https://github.com/Sdaas/hello-karate) - an example created by the community, which tests a Spring Boot REST service using Karate from within IntelliJ, Maven, and Gradle.

## Further Reading

[Karate and Spring Boot on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+spring+boot)

