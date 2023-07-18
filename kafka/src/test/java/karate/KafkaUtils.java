package karate;

import com.intuit.karate.Logger;
import com.intuit.karate.core.ScenarioEngine;
import io.karatelabs.examples.kafka.KarateKafkaConsumer;
import io.karatelabs.examples.kafka.KarateKafkaProducer;
import java.util.List;

public class KafkaUtils {

    private final String topic;
    private final KarateKafkaConsumer consumer;
    private final KarateKafkaProducer producer;

    public KafkaUtils(String topic) {
        this.topic = topic;
        consumer = new KarateKafkaConsumer(topic);
        producer = new KarateKafkaProducer(topic);
    }

    private static Logger logger() {
        ScenarioEngine engine = ScenarioEngine.get();
        return engine.logger;
    }

    public void send(Object data) {
        logger().debug(">> kafka send [{}]", topic);
        producer.send(data);
    }

    public List listen() {
        return consumer.getMessages();
    }

}
