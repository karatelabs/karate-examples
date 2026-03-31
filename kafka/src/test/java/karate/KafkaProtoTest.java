package karate;

import io.karatelabs.core.SuiteResult;
import io.karatelabs.core.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KafkaProtoTest {
    
    @Test
    void testFeature() {
        SuiteResult result = Runner.path("classpath:karate/kafka-proto.feature").parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), result.getErrors().toString());
    }      
    
}
