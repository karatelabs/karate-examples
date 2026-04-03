package karate;

import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoTest {

    @Test
    void testFeature() {
        SuiteResult result = Runner.path("classpath:karate/echo.feature").parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), result.getErrors().toString());
    }

}
