package karate;

import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import io.grpc.Server;
import io.karatelabs.examples.grpc.HelloServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloTest {

    static Server server;

    @BeforeAll
    static void beforeAll() throws Exception {
        server = HelloServer.start(0, false);
    }

    @Test
    void testFeature() {
        SuiteResult result = Runner.path("classpath:karate/hello.feature")
                .systemProperty("grpc.port", server.getPort() + "")
                .parallel(1);
        assertEquals(0, result.getScenarioFailedCount(), result.getErrors().toString());
    }

    @AfterAll
    static void afterAll() {
        server.shutdownNow();
    }

}
