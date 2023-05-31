package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import io.karatelabs.examples.database.Application;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DogsTest {
    
    static final Logger logger = LoggerFactory.getLogger(DogsTest.class);

    @Autowired
    private ServletWebServerApplicationContext webContext;

    @Test
    void testFeature() {
        int port = webContext.getWebServer().getPort();
        logger.debug("server port detected as: {}", port);
        Results results = Runner.path("classpath:karate/dogs.feature")
                .systemProperty("server.port", port + "")
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
