# Karate and RabbitMQ

This sample project demonstrates how you can test RabbitMQ messaging if your implementation is in Java.

For example if you look at [`RMQ.feature`](src/test/java/karate/RMQ.feature) the test is just a few lines. 
Behind the scenes a Rabbit consumer and producer is initialized and the call is made. 

## Running
* Make sure you have followed the instructions to setup RMQ if you are running in yoir local - https://www.rabbitmq.com/download.html
* If you are using Docker compose:
* Run the following commands
  * `docker-compose up -d`
  * `mvn test`
* To stop the Docker containers run `docker-compose down`
* 
### References
* https://x-team.com/blog/set-up-rabbitmq-with-docker-compose/
* https://www.rabbitmq.com/download.html

