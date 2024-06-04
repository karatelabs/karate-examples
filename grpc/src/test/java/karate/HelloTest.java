package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
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
        Results results = Runner.path("classpath:karate/hello.feature")
                .systemProperty("grpc.port", server.getPort() + "")
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

    @AfterAll
    static void afterAll() {
        server.shutdownNow();
    }

}
