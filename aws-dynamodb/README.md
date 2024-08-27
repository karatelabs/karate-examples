# Karate and AWS DynamoDB

Many enterprise users of Karate have successfully included calls to databases or message-queues within their test-automation. This is a great way to test some architectural flows, for example - whether a message resulted in the expected database update.

This is made possible by Karate's unique approach to Java inter-op. It takes only one line of code to load a Java class - and then you can invoke methods or even the constructor. Creating a Java "adapter" is a one-time activity, and after it is in place, the entire team can write functional tests and assertions using the simple and readable syntax that Karate is known for.

This sample project demonstrates how you can test AWS DynamoDB using [Java interop](https://github.com/karatelabs/karate#calling-java). You just need to use the [AWS Java SDK](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html).

The [`DynamoDbUtils.java`](src/test/java/examples/DynamoDbUtils.java) class does the work of connecting to DynamoDB. You need to modify this class to fit your AWS [profile](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-profiles.html), region, table, and schema.

This project is just a simple reference and starting point for what you need to add to the Maven [`pom.xml`](pom.xml) as a dependency.

Now [`dynamo-db.feature`](src/test/java/karate/dynamo-db.feature) is just a few lines and as long as you return a `Map` from your "query", it gets converted to JSON, and you can perform a `match` in Karate.

## Running
This is a normal Java / Maven project so running `mvn clean test` will be sufficient to run tests.

### References
* [RDBMS example](../database/README.md) - how to test Spring Boot and an RDBMS in a single example
* [SSH example](../ssh/README.md) - shows how you can pass data to your Java helper class from your test


