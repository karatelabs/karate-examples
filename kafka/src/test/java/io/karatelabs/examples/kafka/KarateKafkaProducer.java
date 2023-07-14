package io.karatelabs.examples.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import java.util.Properties;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KarateKafkaProducer {
    
    static final Logger logger = LoggerFactory.getLogger(KarateKafkaProducer.class);

    private final String topic;
    private final KafkaProducer<String, GenericRecord> kafka;

    public KarateKafkaProducer(String topic) {
        this.topic = topic;
        kafka = new KafkaProducer(config());
    }

    public void send(Object value) {
        ProducerRecord<String, GenericRecord> record = new ProducerRecord(topic, value);
        kafka.send(record);
    }

    public void close() {
        kafka.close();
    }

    public static Properties config() {
        // refer https://kafka.apache.org/documentation/#producerconfigs
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        // create a safe producer
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        // high throughput producer
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "20"); // linger for 20ms
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024)); // 32 kb
        return props;
    }

}
