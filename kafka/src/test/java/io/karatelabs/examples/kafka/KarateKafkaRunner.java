package io.karatelabs.examples.kafka;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateKafkaRunner {

    static final Logger logger = LoggerFactory.getLogger(KarateKafkaRunner.class);

    @Test
    void testKafka() throws Exception {
        KarateKafkaConsumer consumer = new KarateKafkaConsumer();
        consumer.subscribe("test-topic");
        consumer.listen();
        KarateKafkaProducer producer = new KarateKafkaProducer();
        producer.send("test-topic", "world");
        List messages = consumer.getMessages();
        logger.debug("got messages: {}", messages);        
    }

}
