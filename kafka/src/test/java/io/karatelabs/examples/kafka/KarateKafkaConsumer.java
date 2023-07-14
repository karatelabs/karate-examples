package io.karatelabs.examples.kafka;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateKafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KarateKafkaConsumer.class);

    private final KafkaConsumer<String, GenericRecord> kafka;
    private final CompletableFuture<Boolean> partitionFuture = new CompletableFuture();
    private final CompletableFuture<Boolean> listenFuture = new CompletableFuture();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final List<String> messages = new ArrayList();

    public KarateKafkaConsumer(String topic) {
        kafka = new KafkaConsumer(config());
        listen(topic); // will block until partition is ready
    }

    private void listen(String topic) {
        kafka.subscribe(Collections.singletonList(topic));
        logger.debug("kafka consumer subscibed to topic: {}", topic);
        executor.submit(() -> {
            while (true) {                
                ConsumerRecords<String, GenericRecord> records = kafka.poll(Duration.ofMillis(1000));
                if (records != null && !records.isEmpty()) {
                    for (ConsumerRecord<String, GenericRecord> record : records) {
                        logger.debug("<< kafka consumer: {}", record);
                        // type may be org.apache.avro.util.Utf8 and not String                        
                        Object message = record.value().get("message");
                        messages.add(message + "");
                    }
                    listenFuture.complete(true);
                    break;
                }
                // assignment() can only be called after poll() has been called at least once
                Set<TopicPartition> partitions = kafka.assignment();
                if (partitions.isEmpty()) {
                    try {
                        logger.debug("waiting for partition ...");
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.debug("partitions: {}", partitions);
                    partitionFuture.complete(true);
                }
            }
        });
        try {
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
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        // we use a random id to avoid keeoing track and having to seek to the beginning
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        return props;
    }

}
