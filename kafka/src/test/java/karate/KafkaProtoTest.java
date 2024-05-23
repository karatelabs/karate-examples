package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KafkaProtoTest {
    
    @Test
    void testFeature() {
        Results results = Runner.path("classpath:karate/kafka-proto.feature").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }      
    
}
