package karate;

import com.intuit.karate.Logger;
import com.intuit.karate.core.ScenarioEngine;
import io.karatelabs.examples.rabbitMQ.KarateRMQConsumer;
import io.karatelabs.examples.rabbitMQ.KarateRMQProducer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RMQUtils {

    private final KarateRMQConsumer consumer;
    private final KarateRMQProducer producer;

    public RMQUtils() throws IOException, TimeoutException {
        consumer = new KarateRMQConsumer();
        producer = new KarateRMQProducer();
    }
    
    private static Logger logger() {
        ScenarioEngine engine = ScenarioEngine.get();
        return engine.logger;
    }

    public void send(String message) throws IOException {
        logger().debug(">> RMQ send - {}",message);
        producer.putMessage(message);
    }

    public List listen() throws IOException {
        return consumer.getMessageList();
    }
}
