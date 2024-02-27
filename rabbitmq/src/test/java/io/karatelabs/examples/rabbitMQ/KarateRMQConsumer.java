package io.karatelabs.examples.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateRmqConsumer {

    static final Logger logger = LoggerFactory.getLogger(KarateRmqConsumer.class);
    
    private final ConnectionFactory factory = new ConnectionFactory();
    private final Connection connection;
    private final Channel channel;
    public static List<String> messages = new ArrayList<>();
    
    private final String queueName;

    public KarateRmqConsumer(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        logger.debug("init consumer, waiting for messages ...");
        listen();
    }

    public void listen() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            logger.debug("received message: '{}'", msg);
            messages.add(msg);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    public List<String> getMessageList() {
        return messages;        
    }

}
