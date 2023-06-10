# Karate and RabbitMQ

This sample project demonstrates how you can test RabbitMQ messaging with a Java client.

When you use well designed utility classes, your Karate tests will be clean and only focus on making a call and what data is sent and received.

For example if you look at [`rabbitmq.feature`](src/test/java/karate/rabbitmq.feature) the test is just a few lines. Behind the scenes a Rabbit consumer and producer is initialized and a message is passed. The HTML report even includes details of the "put" operation, which you can easily customize, or extend to other operations.

## Running

* Docker is required to start the RabbitMQ server. There is a `docker-compose.yml` file in this project.
  * `docker-compose up -d`
  * `mvn test`
* To stop the Docker container run `docker-compose down`

### References
* https://x-team.com/blog/set-up-rabbitmq-with-docker-compose/
* https://www.rabbitmq.com/download.html
* [Kafka example](../kafka/README.md)
* [ActiveMQ example](https://github.com/karatelabs/karate/tree/master/karate-netty#consumer-provider-example)
* [Async and Mocks example](https://twitter.com/getkarate/status/1417023536082812935)

