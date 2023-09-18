# Karate Examples
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
* [karate-e2e-tests](https://github.com/karatelabs/karate/tree/master/karate-e2e-tests) - Part of the main Karate project, and also a good example of setting up configuration for multiple browsers and testing in parallel
* [karate-todo](https://github.com/karatelabs/karate-todo) - includes a working UI and [UI tests](https://github.com/karatelabs/karate-todo/tree/main/src/test/java/app/ui), even "hybrid" tests that mix API and UI calls
* [examples/jobserver](https://github.com/karatelabs/karate/tree/master/examples/jobserver) - This Karate project includes some [web-browser tests](https://github.com/karatelabs/karate/tree/master/examples/jobserver/src/test/java/jobtest/web)
* [Visual Testing](https://github.com/karatelabs/karate/tree/master/examples/image-comparison) - refer to this example on how to use Karate's [built-in support for image-comparison](https://github.com/karatelabs/karate/#compare-image)

## Windows Desktop Automation
* [Karate Robot Windows Install Guide](https://github.com/karatelabs/karate/wiki/Karate-Robot-Windows-Install-Guide) - get started testing a simple Windows application

# Integrations
This repository also contains examples of how to use Karate and demonstrates integrations with 3rd party tools and frameworks.

* [Accessibility / Axe](axe/README.md) - Using a JS library to run checks on the DOM in a UI test is very easy
* [ActiveMQ / Async](https://github.com/karatelabs/karate/tree/master/karate-netty#consumer-provider-example) - besides the [demo example](https://github.com/karatelabs/karate/tree/master/karate-demo/src/test/java/mock/contract), refer to [this diagram & example](https://twitter.com/getkarate/status/1417023536082812935) for generic async flows using Java interop
* [Broadcom / CA](https://techdocs.broadcom.com/us/en/ca-enterprise-software/devops/continuous-delivery-director-integrations/1-0/integrations-overview/plug-ins/karate-API-plug-in.html) - Broadcom Continuous Delivery Director has a plug-in for Karate integration
* [BrowserStack](browserstack/README.md) - How to configure Karate to run tests on BrowserStack
* [CLI / Bash](cli/README.md) - How to use Karate to test the command-line or use shell scripts
* [Database](database/README.md) - Include SQL / database calls into functional (and even performance) test suites
* [Docker](docker/README.md) - Easily inject Karate into CI / CD pipelines using Docker
* [GitHub Codespaces](https://github.com/karatelabs/karate/wiki/Get-Started:-GitHub-Codespaces) - open a GitHub project directly in a browser and run and edit Karate tests
* [gRPC](grpc/README.md) - How to test gRPC services with Karate
* [Gatling](https://github.com/karatelabs/karate/tree/master/karate-gatling) - Karate can re-use API functional tests as performance tests !
* [JUnit](https://github.com/karatelabs/karate#junit-5) - There is also a [pure Java API](https://github.com/karatelabs/karate#parallel-execution) to run tests from any Java unit-testing framework such as TestNG
* [Kafka](kafka/README.md) - Using the power of Karate Java interop to test Kafka and async flows
* [Lambdatest](lambdatest/README.md) - How to configure Karate to run tests on Lambdatest
* [Micronaut](micronaut/README.md) - Karate is great for testing Micronaut applications
* [NPM](https://github.com/karatelabs/karate-npm) - there is an option to install Karate as a NPM package and use it from Node / JS
* [Quarkus](quarkus/README.md) - Karate is a good fit for integration testing Quarkus applications
* [RabbitMQ](rabbitmq/README.md) - Using Java to send and receive messages with RabbitMQ
* [Sauce Labs](saucelabs/README.md) - How to configure Karate to run tests on Sauce Labs
* [Slack](slack/README.md) - Refer to blog posts and sample code from the community
* [Spring Boot](spring-boot/README.md) - Resources on testing Spring Boot applications with Karate
* [SSH](ssh/README.md) - how to open an SSH connection and invoke server commands using Karate
* [WebSockets](websockets/README.md) - Built-in support for WebSockets or you can use Java interop for very advanced scenarios
* [Xray](https://docs.getxray.app/display/XRAYCLOUD/Testing+APIs+using+Karate+DSL) - Official documentation from the Xray team on how to integrate Karate