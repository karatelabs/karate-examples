# Karate and Quarkus

This is a "standard" [Quarkus](https://quarkus.io/) project adapted from the Quarkus [getting started guide](https://quarkus.io/guides/getting-started).

A few highlights:

* shows how to run Karate via JUnit using the [`@QuarkusTest`](https://quarkus.io/guides/getting-started-testing#recap-of-http-based-testing-in-jvm-mode) annotation
* uses an injected `@TestHTTPResource` to get the URL after resolving the (dynamic) port that the Quarkus application is running on
* the Karate script [`greeting.feature`](src/test/java/karate/greeting.feature) has two tests for each REST end-point
* shows how the URL (including dynamic port number) is passed to Karate from the Quarkus environment in [`GreetingTest.java`](src/test/java/karate/GreetingTest.java)
* Uses [`<classifier>all</classifier>`](https://github.com/karatelabs/karate#karate-core-fat-jar) in the Maven [`pom.xml`](pom.xml) to avoid conflicts with Quarkus dependencies
* Uses the [`quarkus-logging-logback`](https://quarkiverse.github.io/quarkiverse-docs/quarkus-logging-logback/dev/index.html) extension to bridge Karate with Quarkus' logging system
  * note the over-ride of the `ch.qos.logback:logback-classic` dependency since Quarkus requires a higher version

## Running
Run `mvn test` to execute the JUnit test: [`GreetingTest.java`](src/test/java/karate/GreetingTest.java)




