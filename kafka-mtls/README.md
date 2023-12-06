# Karate Kafka MTLS

This is an additional example for [Karate Kafka](../kafka/README.md) that shows how to use MTLS and client-side authentication.

This section of the test in [kafka-json.feature](src/test/java/karate/kafka-json.feature) sets up client-side SSL:

```cucumber
* configure kafka =
"""
{ 
  'bootstrap.servers': 'localhost:29093',
  'security.protocol': 'SSL',
  'ssl.truststore.location': 'ssl/client.truststore.pkcs12',
  'ssl.truststore.password': 'karate'
}
"""
```

Here the [docker-compose.yml](docker-compose.yml) configures Kafka to use SSL. This example does not use the schema registry.

The certificates and related files in the [/ssl](ssl) folder were created using [conf/commands.txt](conf/commands.txt).

Commands were adapted from these references: 
* [Setting up Encryption](https://developer.confluent.io/courses/security/hands-on-setting-up-encryption)
* [Encrypting Traffic](https://developer.confluent.io/courses/security/hands-on-requiring-encryption-for-broker-traffic/)
* [GitHub Repo](https://github.com/confluentinc/learn-kafka-courses/tree/main/fund-kafka-security)