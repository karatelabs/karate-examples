# Karate Kafka

Karate has native support for Kafka as an optional dependency (non-open source and commercial). Enterprise users can find more information here: https://github.com/karatelabs/karate-addons/tree/main/karate-kafka. 

A license is required for running (for e.g. in CI/CD) and a Karate Labs IDE subscription is required per developer seat.

This sample project shows how to use the Avro and plain-JSON support. Make sure you have a `.karate/karate.lic` file in place before running the example.

## Running
* Docker is required to start the Kafka broker and Zookeeper. There is a `docker-compose.yml` file in this project.
* Run the following commands
  * `docker-compose up -d`
  * `mvn test`
* To stop the Docker containers run `docker-compose down`

## Further Reading
* [kafka-custom](../kafka-custom/README.md) - it is possible to test Kafka by writing the Java integration code yourself and generating Java code, but the approach above is recommended
* [community-example](https://github.com/Sdaas/karate-kafka) - a community project that provides a generic approach to testing Kafka with Karate
