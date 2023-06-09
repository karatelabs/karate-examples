package io.karatelabs.examples.rabbitMQ;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateRMQRunner {

    static final Logger logger = LoggerFactory.getLogger(KarateRMQRunner.class);

    @Test
    void testRMQ() throws Exception {
        KarateRMQConsumer consumer = new KarateRMQConsumer();
        KarateRMQProducer producer = new KarateRMQProducer();
        producer.putMessage("hello world");
        List messages = consumer.getMessageList();
        logger.debug("Reading messages: {}", messages);
        assertEquals(Arrays.asList("hello world"), messages);
        
    }

}
