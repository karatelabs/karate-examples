# Karate and Kafka

This sample project demonstrates how you can test Kafka messaging if your implementation is in Java. It also demonstrates concepts you can use for any case where you use Java interop to connect Karate to any custom code, library or communication protocol.

When you use well designed utility classes, your Karate tests will be clean and only focus on making a call and what data is sent and received.

For example if you look at [`kafka.feature`](src/test/java/karate/kafka.feature) the test is just one line. Behind the scenes a Kafka consumer and producer is initialized and the call is made. The HTML report even includes details of the call and the messages on the wire, which you can easily customize.

Rather than attempt a generic and dynamic approach, this approach shown here can re-use your existing Kafka implementation code-base and domain classes.

## Running
* Docker is required to start the Kafka broker and Zookeeper. There is a `docker-compose.yml` file in this project.
* Run the following commands
  * `docker-compose up -d`
  * `mvn test`
* To stop the Docker containers run `docker-compose down`

## Further Reading
* [karate-kafka](https://github.com/Sdaas/karate-kafka) - a community project that provides a generic approach to testing Kafka with Karate
