package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AllRunner {
    
    @Test
    void testFeature() {
        Results results = Runner.path("classpath:karate").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }    
    
}
