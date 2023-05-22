package io.karatelabs.examples.kafka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateKafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KarateKafkaConsumer.class);

    private final KafkaConsumer<String, Object> kafka;
    private final CompletableFuture<Boolean> partitionFuture = new CompletableFuture();
    private final CompletableFuture<Boolean> listenFuture = new CompletableFuture();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final List messages = new ArrayList();

    public KarateKafkaConsumer(String topic) {
        kafka = new KafkaConsumer(config());
        listen(topic);
    }

    private void listen(String topic) {
        kafka.subscribe(Collections.singletonList(topic));
        logger.debug("kafka consumer subscibed to topic: {}", topic);        
        executor.submit(() -> {
            while (true) {
                logger.debug("kafka consumer polling ...");
                ConsumerRecords<String, Object> records = kafka.poll(Duration.ofMillis(500));
                if (records != null && !records.isEmpty()) {
                    for (ConsumerRecord record : records) {
                        logger.debug("<< kafka consumer: {}", record);
                        messages.add(record.value());
                    }
                    listenFuture.complete(true);
                    break;
                }
                Set<TopicPartition> partitions = kafka.assignment();                
                if (partitions.size() > 0) {
                    partitionFuture.complete(true);
                }
            }
        });
        try {
            logger.debug("kafka consumer waiting for partition ...");
            partitionFuture.get();
            logger.debug("kafka consumer partition ready");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }

    public List getMessages() {
        try {
            logger.debug("kafka consumer waiting for messages ...");
            listenFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

    public void close() {
        kafka.close();
        executor.shutdown();
    }

    public static Properties config() {
        // refer https://kafka.apache.org/documentation/#consumerconfigs
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "karate-kafka-default-consumer-group");
        return props;
    }

}
