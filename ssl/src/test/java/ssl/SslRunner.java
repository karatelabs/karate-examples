package ssl;

import com.intuit.karate.junit5.Karate;

class SslRunner {

    @Karate.Test
    Karate testKeystore() {
        return Karate.run("classpath:ssl/ssl-keystore.feature");
    }

    @Karate.Test
    Karate testTruststore() {
        return Karate.run("classpath:ssl/ssl-truststore.feature");
    }

}
