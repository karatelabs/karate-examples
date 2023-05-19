package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GrpcTest {    
    
    @Test
    void testFeature() {
        Results results = Runner.path("classpath:karate/grpc.feature").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }      
    
}
