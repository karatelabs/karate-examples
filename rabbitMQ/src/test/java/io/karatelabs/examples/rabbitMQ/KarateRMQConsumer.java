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

public class KarateRMQConsumer {
    static final Logger logger = LoggerFactory.getLogger(KarateRMQConsumer.class);


    private final static String QUEUE_NAME = "MyQueue";
    ConnectionFactory factory = new ConnectionFactory();
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    public static List<String> messageList = new ArrayList<>();

    public KarateRMQConsumer() throws IOException, TimeoutException {
        factory.setHost("localhost");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        logger.info("Initialised RMQ");
        listen();
    }
    public void listen() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String str= new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + str + "'");
            messageList.add(str);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

    public List<String> getMessageList(){

        try {
            logger.debug("Getting messages ...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messageList;
    }

}
