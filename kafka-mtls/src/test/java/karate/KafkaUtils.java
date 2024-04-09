package karate;

import com.intuit.karate.Json;
import io.karatelabs.kafka.KarateKafkaProducer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaUtils {

    public static void send() {
        Map<String, Object> map = new HashMap();
        map.put("bootstrap.servers", "localhost:29093");
        map.put("security.protocol", "SSL");
        map.put("ssl.truststore.location", "ssl/client.truststore.pkcs12");
        map.put("ssl.truststore.password", "karate");
        KarateKafkaProducer producer = new KarateKafkaProducer(map);
        String json = Json.of("{ hello: 'world' }").toString();
        ProducerRecord pr = new ProducerRecord("test-topic", null, "first", json.getBytes(), null);
        producer.send(pr);
    }

}
