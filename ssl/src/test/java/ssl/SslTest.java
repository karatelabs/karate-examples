package ssl;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import io.karatelabs.examples.ssl.TestService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SslTest {

    @Test
    void testAll() {
        TestService.main(new String[]{});
        Results results = Runner.path("classpath:ssl").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }

}
