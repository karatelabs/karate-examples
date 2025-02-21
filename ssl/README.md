# Karate and SSL

This is a "standard" [Spring Boot](https://spring.io/projects/spring-boot) project.

It shows how to test a server that uses a trust-store and / or keystore for security.

The relevant Karate documentation can be found here: [X509 Certificate Authentication](https://github.com/karatelabs/karate#x509-certificate-authentication).

Run the server from the IDE or via Maven using this command:

```
mvn -Dexec.mainClass=io.karatelabs.examples.ssl.TestService compile exec:java
```

This will start an HTTPS server on port 8080.

Now you can run the two Karate tests individually from the IDE.

Or by using the [SslRunner](src/test/java/ssl/SslRunner.java) JUnit helper.

```
mvn test -Dtest=SslRunner
```

You can also run the test suite that will start the server automatically.

```
mvn test
```

## Further Reading
* [Karate and Spring Boot](../spring-boot/README.md)
* [Karate and Databases](../database/README.md)



