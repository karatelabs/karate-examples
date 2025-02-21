package io.karatelabs.examples.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class TestService {

    @RestController
    @RequestMapping("/test")
    class TestController {

        @GetMapping
        public String test() {
            return "{ \"success\": true }";
        }

    }

    public static void main(String[] args) {
        String[] customArgs = {
                "--server.port=8080",
                "--server.ssl.key-store=src/main/resources/server-keystore.p12",
                "--server.ssl.key-store-password=karate-mock",
                "--server.ssl.keyStoreType=PKCS12",
                "--server.ssl.keyAlias=karate-mock"};
        SpringApplication.run(TestService.class, customArgs);
    }

}
