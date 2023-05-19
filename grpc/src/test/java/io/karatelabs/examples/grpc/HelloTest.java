package io.karatelabs.examples.grpc;

import io.grpc.Server;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class HelloTest {
    
    static final Logger logger = LoggerFactory.getLogger(HelloTest.class);
 
    @Test
    void testClientServer() throws Exception {
        Server server = HelloServer.start(0);
        HelloClient client = new HelloClient("localhost", server.getPort());
        String result = client.hello("world");
        logger.debug("response is: {}", result);
        client.shutdown();
    }    
    
}
