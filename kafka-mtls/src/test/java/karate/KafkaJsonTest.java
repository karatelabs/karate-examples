package karate;

import io.karatelabs.core.SuiteResult;
import io.karatelabs.core.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class KafkaJsonTest {

    @Test
    void testFeature() {
        SuiteResult result = Runner.path("classpath:karate/kafka-json.feature").parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), result.getErrors().toString());
    }

}
