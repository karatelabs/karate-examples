package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.core.MockServer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class KafkaHybridTest {

    static MockServer server;

    @BeforeAll
    static void beforeAll() {
        server = MockServer.feature("classpath:karate/mock.feature").build();
    }

    @Test
    void testHybrid() {
        Results results = Runner.path("classpath:karate/kafka-hybrid.feature")
                .systemProperty("server.port", server.getPort() + "")
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
