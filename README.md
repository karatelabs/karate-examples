# Karate Examples

> [!IMPORTANT]
> Please note that Karate 1.4.1 [will not work if your Java version is 22 or greater](https://github.com/karatelabs/karate/issues/2542). Try to remain on a lower version of Java or upgrade your Karate version to 1.5.X
> Also Karate 1.5.1 [will not work if your Java version is 24 or greater](https://github.com/karatelabs/karate/issues/2705). Use Karate 1.5.2 if needed.

* [karate-template](https://github.com/karatelabs/karate-template) - use this GitHub template if you are looking for a quick-start "skeleton" Karate project for API testing
* [karate-todo](https://github.com/karatelabs/karate-todo) - great for training or demo-ing all capabilities of Karate. It includes an app that has a working front-end UI and back-end API. Examples of API tests, API performance tests, API mocks and Web-Browser automation are included.


## API Testing
* [karate-demo](https://github.com/karatelabs/karate/tree/master/karate-demo) - This is not designed to be a stand-alone project, and is more of a regression test-suite for Karate itself. But it has a lot of examples of how to use Karate for API testing
* [karate-todo](https://github.com/karatelabs/karate-todo) - includes a working API and [examples](https://github.com/karatelabs/karate-todo/tree/main/src/test/java/app/api)
* [spring-boot](spring-boot) - Examples of how to test Spring Boot applications
* [folio-integration-tests](https://github.com/folio-org/folio-integration-tests/tree/master) - This is a complex external open-source project that uses Karate, and all the tests and project-structure can be viewed. This one is a good example: [permissions.feature](https://github.com/folio-org/folio-integration-tests/blob/9254190be84869a09b093acca05b2703edd3b55f/mod-permissions/src/main/resources/domain/mod-permissions/features/permissions.feature)

## API Performance Testing
* [examples/gatling](https://github.com/karatelabs/karate/tree/master/examples/gatling) - a stand-alone project that demonstrates how to use Karate with Gatling for performance testing
* [karate-todo](https://github.com/karatelabs/karate-todo) - includes a working API and [performance test](https://github.com/karatelabs/karate-todo/tree/main/src/test/java/app/perf)
* [karate-gatling](https://github.com/karatelabs/karate/tree/master/karate-gatling/src/test/scala/mock) - The main documentation also includes an example

## API Mocks
* [examples/gatling](https://github.com/karatelabs/karate/tree/master/examples/gatling) - although this project is mainly to demo performance testing, it also has an example of using a Karate API [mock](https://github.com/karatelabs/karate/blob/master/examples/gatling/src/test/java/mock/mock.feature).
* [karate-todo](https://github.com/karatelabs/karate-todo) - includes a working API and [equivalent mock](https://github.com/karatelabs/karate-todo/blob/main/src/test/java/app/mock/mock.feature) and if you are interested in the new [pure-JS option](https://github.com/karatelabs/karate/wiki/Karate-JavaScript-Mocks), there is an [example](https://github.com/karatelabs/karate-todo/blob/main/src/main/java/app/api/todos.js)
* [consumer-driven-contracts](https://github.com/karatelabs/karate/tree/master/examples/consumer-driven-contracts) - Karate's approach to contract-testing depends on mocks, and this project has a [good example](https://github.com/karatelabs/karate/blob/master/examples/consumer-driven-contracts/payment-producer/src/test/java/payment/producer/mock/payment-mock.feature).

## Web Browser Automation
* [karate-e2e-tests](https://github.com/karatelabs/karate/tree/master/karate-e2e-tests) - Part of the main Karate project, and also a good example of setting up configuration for multiple browsers and testing in parallel using the [karate-chrome](https://github.com/karatelabs/karate/tree/master/karate-core#karate-chrome) Docker container
* [karate-todo](https://github.com/karatelabs/karate-todo) - includes a working UI and [UI tests](https://github.com/karatelabs/karate-todo/tree/main/src/test/java/app/ui), even "hybrid" tests that mix API and UI calls
* [Visual Testing](https://github.com/karatelabs/karate/tree/master/examples/image-comparison) - refer to this example on how to use Karate's [built-in support for image-comparison](https://github.com/karatelabs/karate/#compare-image)
* Note that Karate UI tests can be run using [Sauce Labs](https://github.com/karatelabs/karate-examples/blob/main/saucelabs/README.md), [BrowserStack](https://github.com/karatelabs/karate-examples/blob/main/browserstack/README.md), [Lambdatest](https://github.com/karatelabs/karate-examples/blob/main/lambdatest/README.md) or any other infrastucture that supports the [WebDriver (a.k.a. Selenium) protocol](https://www.w3.org/TR/webdriver/) - which means that you have plenty of options for running tests in parallel and you can even self-host the infrastructure to do so

## Windows Desktop Automation
* [Karate Robot Windows Install Guide](https://github.com/karatelabs/karate/wiki/Karate-Robot-Windows-Install-Guide) - get started testing a simple Windows application

# Integrations
This repository also contains examples of how to use Karate and demonstrates integrations with 3rd party tools and frameworks.

* [Accessibility / Axe](axe/README.md) - Using a JS library to run checks on the DOM in a UI test is very easy
* [ActiveMQ / Async](https://github.com/karatelabs/karate/tree/master/karate-netty#consumer-provider-example) - besides the [demo example](https://github.com/karatelabs/karate/tree/master/karate-demo/src/test/java/mock/contract), refer to [this diagram & example](https://twitter.com/getkarate/status/1417023536082812935) for generic async flows using Java interop
* [Allure](https://github.com/allure-examples/allure-karate-example) - Official support for [Allure Report](https://allurereport.org)
* [AWS](aws/README.md) - Any AWS service can be integrated using the [AWS Java SDK](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html) and [Java Interop](https://github.com/karatelabs/karate#calling-java).
* [Broadcom / CA](https://techdocs.broadcom.com/us/en/ca-enterprise-software/devops/continuous-delivery-director-integrations/1-0/integrations-overview/plug-ins/karate-API-plug-in.html) - Broadcom Continuous Delivery Director has a plug-in for Karate integration
* [BrowserStack](browserstack/README.md) - How to configure Karate to run tests on BrowserStack
* [CLI / Bash](cli/README.md) - How to use Karate to test the command-line or use shell scripts
* [Database](database/README.md) - Include SQL / database calls into functional (and even performance) test suites
* [Docker](docker/README.md) - Easily inject Karate into CI / CD pipelines using Docker
* [GitHub Codespaces](https://github.com/karatelabs/karate/wiki/Get-Started:-GitHub-Codespaces) - open a GitHub project directly in a browser and run and edit Karate tests
* [gRPC](grpc/README.md) - seamlessly test gRPC services with Karate
* [Gatling](https://github.com/karatelabs/karate/tree/master/karate-gatling) - Karate can re-use API functional tests as performance tests !
* [Gradle](https://github.com/karatelabs/karate/wiki/Gradle) - Karate has very good support for Gradle
* [Jacoco](https://github.com/karatelabs/karate/tree/master/karate-demo#code-coverage-using-jacoco) - use Jacoco for code-coverage
* [JBang](jbang/README.md) - using only [JBang](https://www.jbang.dev) to run Karate tests and even manage custom Java code and classpath libraries without installing Java
* [JUnit](https://github.com/karatelabs/karate#junit-5) - There is also a [pure Java API](https://github.com/karatelabs/karate#parallel-execution) to run tests from any Java unit-testing framework such as TestNG
* [Kafka](kafka/README.md) - seamlessly test Kafka and async flows
* [Lambdatest](lambdatest/README.md) - How to configure Karate to run tests on Lambdatest
* [Maven](https://github.com/karatelabs/karate#maven) - Karate has excellent support for Maven, most of the examples here are Maven projects
* [Micronaut](micronaut/README.md) - Karate is great for testing Micronaut applications
* [NPM](https://github.com/karatelabs/karate-npm) - there is an option to install Karate as a NPM package and use it from Node / JS
* [Quarkus](quarkus/README.md) - Karate is a good fit for integration testing Quarkus applications
* [RabbitMQ](rabbitmq/README.md) - Using Java to send and receive messages with RabbitMQ
* [ReportPortal](https://github.com/reportportal/agent-java-karate) official integration agent from ReportPortal.
* [Sauce Labs](saucelabs/README.md) - How to configure Karate to run tests on Sauce Labs
* [Slack](slack/README.md) - Refer to blog posts and sample code from the community
* [Spring Boot](spring-boot/README.md) - Resources on testing Spring Boot applications with Karate
* [SSH](ssh/README.md) - how to open an SSH connection and invoke server commands using Karate
* [SSL](ssl/README.md) - how to test APIs that require MTLS and setting up X509 certificates on the HTTP client side
* [WebSocket](websocket/README.md) - Built-in support for WebSocket or you can use Java interop for very advanced scenarios
* [Xray](https://docs.getxray.app/space/XRAY/301410448/Testing+APIs+using+Karate+DSL) - Official documentation from the Xray team on how to integrate Karate