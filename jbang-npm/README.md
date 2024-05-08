# Using Java Libraries with NPM

If your organization includes Node and Java applications you may find it beneficial to share Java libraries with your Node counterparts writing Karate tests. This may benefit a range of tooling or capabilities solved for Java but not portable to Node... until now!

Using the steps in this tutorial your Node teams will be able to use Java libraries in their Karate tests, all without having to install Java or Maven/Gradle to manage the dependencies for them.

## Background
Karate's NPM library uses JBang to install Java and support the Karate runtime. We can leverage JBang's DEPS directive to include external dependencies the same way we would with Maven's `<dependency>`. If you are not familiar with JBang's DEPS or FILES directives you might take a look at their documentation.

* [DEPS](https://www.jbang.dev/documentation/guide/latest/dependencies.html)  
* [FILES](https://www.jbang.dev/documentation/guide/latest/organizing.html)

The example used in this tutorial buils on the existing [jbang](../jbang/README.md) tutorial that uses the [Faker library](https://github.com/DiUS/java-faker).

## Hello.java

Create a file called `Hello.java`. Paste the following into `Hello.java`.

```java
///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS com.intuit.karate:karate-core:1.4.1:all
//DEPS com.github.javafaker:javafaker:1.0.2

class Hello {

    public static void main(String[] args) throws Exception {
        com.intuit.karate.Main.main(args);
    }
}
```

The first line `///usr/bin/env jbang "$0" "$@" ; exit $?` tricks bash, zsh etc into running this as a script. The `//DEPS` lines after define each of the libraries to include. JBang will download those libraries just like Maven or Gradle does for dependencies.

The main method is required to start Karate and pass through arguments.


## package.json

Create a file called `package.json`. Paste the following into `package.json`.

```json
{
  "scripts": {
    "test": "node test.js"
  },
  "devDependencies": {
    "@karatelabs/karate": "^0.2.2"
  },
  "dependencies": {
    "karate": "^1.0.0"
  }
}
```

WARNING: The line `"test": "node test.js"` is important. Specifically, the value: `test.js`. Please use do not change `test.js` unless you update it everywhere else we refer to it in the steps below. It's best to keep it as is.

## test.js

Create a file called `test.js`. Paste the following into `test.js`.

```javascript
#! /usr/bin/env node
const karate = require('@karatelabs/karate');
karate.jvm.args = `Hello.java`
karate.exec();
```

The key line is ```karate.jvm.args = `Hello.java` ```. This passes through to JBang the Hello.java class to compile and is used to include the dependencies.

## javaTest.feature

Create a file called `javaTest.feature`. Paste the following into `javaTest.feature`.

```gherkin
Feature: Example of NPM usage of Java library in Karate feature file.

    Background:
    * def faker = new com.github.javafaker.Faker()

    Scenario:
    * def name = faker.name().fullName()

    Given url 'https://httpbin.org/post'
    And request { name: '#(name)' }
    When method post
    Then status 200
    And match response.json.name == name
```

The two lines:

```gherkin

    Background:
    * def faker = new com.github.javafaker.Faker()

    Scenario:
    * def name = faker.name().fullName()
```

get an instance of Faker and then use it to generate a full name: `faker.name().fullName()`


## Install Karate

After you have your Karate folder setup, you need to have NPM install Karate. This is a one time operation. Run: `npm install karate`.

Successful execution will output:

```shell
added 23 packages, and audited 24 packages in 5s

5 packages are looking for funding
  run `npm fund` for details

found 0 vulnerabilities
```

## Run the Karate Test

Run the test using: `npm run test javaTest.feature`

```shell
> test
> node test.js javaTest.feature

using jbang: Hello.java com.intuit.karate:karate-core:LATEST:all javaTest.feature
12:13:52.691 [main]  INFO  com.intuit.karate - Karate version: 1.4.1
12:13:54.786 [main]  DEBUG com.intuit.karate - request:
1 > POST https://httpbin.org/post
1 > Content-Type: application/json; charset=UTF-8
1 > Content-Length: 20
1 > Host: httpbin.org
1 > Connection: Keep-Alive
1 > User-Agent: Apache-HttpClient/4.5.14 (Java/11.0.11)
1 > Accept-Encoding: gzip,deflate
{"name":"Ian Morar"}

12:13:55.649 [main]  DEBUG com.intuit.karate - response time in milliseconds: 853
1 < 200
1 < Date: Tue, 07 May 2024 19:13:55 GMT
1 < Content-Type: application/json
1 < Connection: keep-alive
1 < Server: gunicorn/19.9.0
1 < Access-Control-Allow-Origin: *
1 < Access-Control-Allow-Credentials: true
1 < Content-Length: 516
{
  "args": {}, 
  "data": "{\"name\":\"Ian Morar\"}", 
  "files": {}, 
  "form": {}, 
  "headers": {
    "Accept-Encoding": "gzip,deflate", 
    "Content-Length": "20", 
    "Content-Type": "application/json; charset=UTF-8", 
    "Host": "httpbin.org", 
    "User-Agent": "Apache-HttpClient/4.5.14 (Java/11.0.11)", 
    "X-Amzn-Trace-Id": "Root=1-663a7d73-672d8ccb2f05af3e6a7cc8ab"
  }, 
  "json": {
    "name": "Ian Morar"
  }, 
  "origin": "50.47.158.189, 163.116.251.114", 
  "url": "https://httpbin.org/post"
}


---------------------------------------------------------
feature: javaTest.feature
scenarios:  1 | passed:  1 | failed:  0 | time: 1.6544
---------------------------------------------------------

12:13:57.853 [main]  INFO  com.intuit.karate.Suite - <<pass>> feature 1 of 1 (0 remaining) javaTest.feature
Karate version: 1.4.1
======================================================
elapsed:   4.93 | threads:    1 | thread time: 1.65 
features:     1 | skipped:    0 | efficiency: 0.34
scenarios:    1 | passed:     1 | failed: 0
======================================================

HTML report: (paste into browser to view) | Karate version: 1.4.1
file:///Users/byarger/dev/karate-examples/jbang-npm/target/karate-reports/karate-summary.html
===================================================================
```

The request payload contains the generated name:

```json
{
"json": {
    "name": "Ian Morar"
  }
}
```

Every time you run the test, the name will change. Congrats! You've successfully consumed Java libraries in your Karate test without having to install Java or Maven/Gradle to manage the Java dependencies for you.