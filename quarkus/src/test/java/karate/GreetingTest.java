package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GreetingTest {

    @TestHTTPResource("/")
    URL urlBase;
    
    @Test
    void testGreeting() {
        Results results = Runner.path("classpath:karate/greeting.feature")
                .systemProperty("url.base", urlBase.toString())
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());        
    }

}
