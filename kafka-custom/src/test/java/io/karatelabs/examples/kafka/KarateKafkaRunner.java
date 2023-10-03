package io.karatelabs.examples.kafka;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateKafkaRunner {

    static final Logger logger = LoggerFactory.getLogger(KarateKafkaRunner.class);

    @Test
    void testKafka() throws Exception {
        String topic = "test-topic";
        KarateKafkaConsumer consumer = new KarateKafkaConsumer(topic);
        KarateKafkaProducer producer = new KarateKafkaProducer(topic);
        producer.send("hello world");
        List messages = consumer.getMessages();
        logger.debug("got messages: {}", messages);
        assertEquals(Arrays.asList("hello world"), messages);
        
    }

}
