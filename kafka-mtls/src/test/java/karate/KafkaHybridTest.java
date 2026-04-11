package karate;

import io.karatelabs.core.SuiteResult;
import io.karatelabs.core.Runner;
import io.karatelabs.core.MockServer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class KafkaHybridTest {

    static MockServer server;

    @BeforeAll
    static void beforeAll() {
        server = MockServer.feature("classpath:karate/mock.feature").start();
    }

    @Test
    void testHybrid() {
        SuiteResult result = Runner.path("classpath:karate/kafka-hybrid.feature")
                .systemProperty("server.port", server.getPort() + "")
                .parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), result.getErrors().toString());
    }

}
