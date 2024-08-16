# Karate and AWS DynamoDB

This sample project demonstrates how you can test AWS DynamoDB using [Java interop](https://github.com/karatelabs/karate#calling-java). You just need to use the [AWS Java SDK](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html).

The [`DynamoDbUtils.java`](src/test/java/examples/DynamoDbUtils.java) class does the work of connecting to DynamoDB. You need to modify this class to fit your AWS [profile](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-profiles.html), region, table, and schema.

This project is just a simple reference and starting point for what you need to add to the Maven [`pom.xml`](pom.xml) as a dependency.

Now [`dynamo-db.feature`](src/test/java/karate/dynamo-db.feature) is just a few lines and as long as you return a `Map` from your "query", it gets converted to JSON, and you can perform a `match` in Karate.

## Running
This is a normal Java / Maven project so running `mvn clean test` will be sufficient to run tests.

### References
* [RDBMS example](../database/README.md) - how to test Spring Boot and an RDBMS in a single example
* [SSH example](../ssh/README.md) - shows how you can pass data to your Java helper class from your test


