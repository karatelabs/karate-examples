package io.karatelabs.examples.grpc;

import io.grpc.Server;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


class HelloJavaRunner {

    static final Logger logger = LoggerFactory.getLogger(HelloJavaRunner.class);

    static Server server;
    HelloClient client;

    @BeforeAll
    static void beforeAll() throws Exception {
        server = HelloServer.start(0, true);
    }

    @AfterAll
    static void afterAll() {
        server.shutdownNow();
    }

    @BeforeEach
    void beforeEach() {
        client = new HelloClient(server.getPort(), true);
    }

    @AfterEach
    void afterEach() throws Exception {
        client.shutdown();
    }

    @Test
    void testHello() throws Exception {
        String result = client.hello("world");
        logger.debug("response is: {}", result);
    }

    @Test
    void testLotsOfReplies() throws Exception {
        List<String> result = client.lotsOfReplies("world");
        logger.debug("response is: {}", result);
    }

    @Test
    void testLotsOfGreetings() throws Exception {
        String result = client.lotsOfGreetings("foo", "bar", "baz");
        logger.debug("response is: {}", result);
    }

    @Test
    void testBidiHello() throws Exception {
        List<String> result = client.bidiHello("foo", "bar", "baz");
        logger.debug("response is: {}", result);
    }

}
