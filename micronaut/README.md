# Karate and Micronaut

This is a "standard" [Micronaut](https://micronaut.io) project adapted from the Micronaut [getting started guide](https://guides.micronaut.io/latest/creating-your-first-micronaut-app-maven-java.html).

A few highlights:

* shows how to run Karate via JUnit using the [`@MicronautTest`](https://micronaut-projects.github.io/micronaut-test/latest/guide) annotation
* uses an `@Inject`-ed `EmbeddedServer` to get the URL after resolving the (dynamic) port that the Micronaut application is running on
  * in test mode, `(server:) port: -1` in [src/test/java/application-test.yml](src/test/java/application-test.yml) configures the server to start on a random and unused port
* the Karate script [`hello.feature`](src/test/java/karate/hello.feature) has a test for the `/hello` end point
* shows how the URL (including dynamic port number) is passed to Karate from the Micronaut environment in [`HelloTest.java`](src/test/java/karate/HelloTest.java)
* Uses [`<classifier>all</classifier>`](https://github.com/karatelabs/karate#karate-core-fat-jar) in the Maven [`pom.xml`](pom.xml) to avoid conflicts with Micronaut dependencies


## Graal
Micronaut heavily depends on Graal and so does Karate which requires us to align the versions pulled in by the [`pom.xml`](pom.xml). This is why we have the additional entries for `org.graalvm.js:js-scriptengine` and `org.graalvm.js:js` and we force them to be the version that Micronaut needs.

If you see the application hanging or things like a [`NoClassDefFoundError` and `polyglot` or "graal"](https://stackoverflow.com/q/72952986/143475) mentioned in the stack traces, you need to adjust this.


## Running
Run `mvn test` to execute the JUnit test: [`HelloTest.java`](src/test/java/karate/HelloTest.java)




