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
        consumer = new KarateKafkaConsumer();
        consumer.subscribe(topic);
        consumer.listen();
        producer = new KarateKafkaProducer();
    }
    
    private static Logger logger() {
        ScenarioEngine engine = ScenarioEngine.get();
        return engine.logger;
    }    

    public void send(String message) {
        logger().debug(">> kafka send [{}] - {}", topic, message);      
        producer.send(topic, message);
    }

    public List listen() {
        return consumer.getMessages();
    }

}
