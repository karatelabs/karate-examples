package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@MicronautTest
class HelloTest {
    
    @Inject
    EmbeddedServer server;    
    
    @Test
    void testGreeting() {        
        Results results = Runner.path("classpath:karate/hello.feature")
                .systemProperty("url.base", server.getURL().toString())
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
