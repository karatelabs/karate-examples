package io.karatelabs.examples.kafka;

import com.intuit.karate.Json;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AvroUtilsRunner {
    
    static final Logger logger = LoggerFactory.getLogger(AvroUtilsRunner.class);

    @Test
    void testAvro() {
        Schema schema = AvroUtils.toSchema("src/test/java/karate/hello.avsc");
        String json = Json.of("{ message: 'hello', info: { first: 5, second: true } }").toString();
        GenericRecord record = AvroUtils.fromJson(schema, json);
        logger.debug("record: {}", record);
        String result = AvroUtils.toJson(record);
        logger.debug("result: {}", result);
    }

}
