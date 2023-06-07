package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
class GreetingTest {
    
    private static final Logger logger = LoggerFactory.getLogger(GreetingTest.class); 

    @TestHTTPResource("/")
    URL urlBase;
    
    @Test
    void testGreeting() {
        logger.debug("url base is: {}", urlBase);
        Results results = Runner.path("classpath:karate/greeting.feature")
                .systemProperty("url.base", urlBase.toString())
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());        
    }

}
