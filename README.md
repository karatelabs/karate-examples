# Karate Examples

## Karate Template
Use the [karate-template](https://github.com/karatelabs/karate-template) GitHub project if you are looking for a quick-start "skeleton" to create a new Karate project.

## API Testing
* [karate-demo](https://github.com/karatelabs/karate/tree/master/karate-demo) - This is not designed to be a stand-alone project, and is more of a regression test-suite for Karate itself. But it has a lot of examples of how to use Karate for API testing
* [spring-boot](spring-boot) - Examples of how to test Spring Boot applications
* [folio-integration-tests](https://github.com/folio-org/folio-integration-tests/tree/master) - This is a complex external open-source project that uses Karate, and all the tests and project-structure can be viewed. This one is a good example: [permissions.feature](https://github.com/folio-org/folio-integration-tests/blob/9254190be84869a09b093acca05b2703edd3b55f/mod-permissions/src/main/resources/domain/mod-permissions/features/permissions.feature)

## API Performance Testing
* [examples/gatling](https://github.com/karatelabs/karate/tree/master/examples/gatling) - a stand-alone project that demonstrates how to use Karate with Gatling for performance testing
* [karate-gatling](https://github.com/karatelabs/karate/tree/master/karate-gatling/src/test/scala/mock) - The main documentation also includes an example

## API Mocks
* [examples/gatling](https://github.com/karatelabs/karate/tree/master/examples/gatling) - although this project is mainly to demo performance testing, it also has an example of using a Karate API [mock](https://github.com/karatelabs/karate/blob/master/examples/gatling/src/test/java/mock/mock.feature).
* [consumer-driven-contracts](https://github.com/karatelabs/karate/tree/master/examples/consumer-driven-contracts) - Karate's approach to contract-testing depends on mocks, and this project has a [good example](https://github.com/karatelabs/karate/blob/master/examples/consumer-driven-contracts/payment-producer/src/test/java/payment/producer/mock/payment-mock.feature).

## Web Browser Automation
* [karate-e2e-tests](https://github.com/karatelabs/karate/tree/master/karate-e2e-tests) - Part of the main Karate project, and also a good example of setting up configuration for multiple browsers and testing in parallel
* [examples/jobserver](https://github.com/karatelabs/karate/tree/master/examples/jobserver) - This Karate project includes some [web-browser tests](https://github.com/karatelabs/karate/tree/master/examples/jobserver/src/test/java/jobtest/web)

# Integrations
This repository also contains examples of how to use Karate and demonstrates integrations with 3rd party tools and frameworks.

* [Sauce Labs](sauce-labs/README.md) - How to configure Karate to run tests on Sauce Labs
* [Spring Boot](spring-boot/README.md) - Resources on testing Spring Boot applications with Karate