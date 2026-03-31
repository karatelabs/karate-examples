package karate;

import io.karatelabs.core.SuiteResult;
import io.karatelabs.core.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class KafkaTest {    
    
    @Test
    void testFeature() {
        SuiteResult result = Runner.path("classpath:karate/kafka.feature").parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), result.getErrors().toString());
    }      
    
}
