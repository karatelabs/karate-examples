package io.karatelabs.examples.rabbitMQ;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateRmqRunner {

    static final Logger logger = LoggerFactory.getLogger(KarateRmqRunner.class);

    @Test
    void testRMQ() throws Exception {
        String queueName = "my-queue";
        KarateRmqConsumer consumer = new KarateRmqConsumer(queueName);
        KarateRmqProducer producer = new KarateRmqProducer(queueName);
        producer.putMessage("hello world");
        List messages = consumer.getMessageList();
        logger.debug("messages: {}", messages);
        assertEquals(Arrays.asList("hello world"), messages);
        
    }

}
