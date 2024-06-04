package karate;

import com.intuit.karate.Logger;
import com.intuit.karate.core.ScenarioEngine;
import io.grpc.Server;
import io.karatelabs.examples.grpc.HelloClient;
import io.karatelabs.examples.grpc.HelloServer;

public class GrpcUtils {    
    
    private static final GrpcUtils INSTANCE = new GrpcUtils();
    
    private final HelloClient client;
    
    private GrpcUtils() {
        try {
            Server server = HelloServer.start(0);
            client = new HelloClient("localhost", server.getPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }
    
    private static Logger logger() {
        ScenarioEngine engine = ScenarioEngine.get();
        return engine.logger;
    }
    
    // the logging will appear in the html report for grpc.feature
    public static String hello(String message) {
        Logger logger = logger();
        logger.debug("grpc call to hello()");
        logger.debug("====================");
        logger.debug(">> hello: {}", message);
        String result = INSTANCE.client.hello(message);
        logger.debug("<< hello: {}", result);
        return result;
    }
    
}
