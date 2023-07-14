package karate;

import com.intuit.karate.Logger;
import com.intuit.karate.core.ScenarioEngine;
import io.karatelabs.examples.kafka.KarateKafkaConsumer;
import io.karatelabs.examples.kafka.KarateKafkaProducer;
import java.util.List;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.LoggerFactory;

public class KafkaUtils {

    static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KafkaUtils.class);

    private final String topic;
    private final KarateKafkaConsumer consumer;
    private final KarateKafkaProducer producer;
    private final Schema helloSchema;

    public KafkaUtils(String topic) {
        this.topic = topic;
        consumer = new KarateKafkaConsumer(topic);
        producer = new KarateKafkaProducer(topic);
        Schema.Parser parser = new Schema.Parser();
        helloSchema = parser.parse(HELLO_SCHEMA);
    }

    private static Logger logger() {
        ScenarioEngine engine = ScenarioEngine.get();
        return engine.logger;
    }

    public void send(String message) {
        logger().debug(">> kafka send [{}] - {}", topic, message);
        GenericRecord record = new GenericData.Record(helloSchema);
        record.put("message", message);
        producer.send(record);
    }

    public List listen() {
        return consumer.getMessages();
    }

    private static final String HELLO_SCHEMA = "{\n"
            + "  \"namespace\": \"io.karatelabs.examples\",\n"
            + "  \"type\": \"record\",\n"
            + "  \"name\": \"Hello\",\n"
            + "  \"fields\": [\n"
            + "    {\n"
            + "      \"name\": \"message\",\n"
            + "      \"type\": \"string\"\n"
            + "    }\n"
            + "  ]\n"
            + "}";

}
