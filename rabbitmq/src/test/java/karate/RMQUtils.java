package karate;

import com.intuit.karate.Logger;
import com.intuit.karate.core.ScenarioEngine;
import io.karatelabs.examples.rabbitMQ.KarateRmqConsumer;
import io.karatelabs.examples.rabbitMQ.KarateRmqProducer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RmqUtils {

    private final KarateRmqConsumer consumer;
    private final KarateRmqProducer producer;

    public RmqUtils(String queueName) throws IOException, TimeoutException {
        consumer = new KarateRmqConsumer(queueName);
        producer = new KarateRmqProducer(queueName);
    }

    private static Logger logger() {
        ScenarioEngine engine = ScenarioEngine.get();
        return engine.logger;
    }

    public void send(String message) throws IOException {
        logger().debug(">> RMQ send - {}", message);
        producer.putMessage(message);
    }

    public List listen() throws IOException {
        return consumer.getMessageList();
    }

}
