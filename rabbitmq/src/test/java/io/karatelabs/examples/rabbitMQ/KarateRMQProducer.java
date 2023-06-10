package io.karatelabs.examples.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class KarateRmqProducer {

    private static final Logger logger = LoggerFactory.getLogger(KarateRmqProducer.class);

    private final Channel channel;
    private final String queueName;

    public KarateRmqProducer(String queueName) {
        this.queueName = queueName;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection;
            connection = factory.newConnection();
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setHost("localhost");
            factory.setPort(5672);
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            logger.debug("init producer");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void putMessage(String msg) throws IOException {
        channel.basicPublish("", queueName, null, msg.getBytes());
        logger.debug("put message: '{}'", msg);
    }

}
