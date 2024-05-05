# Karate and JBang

[JBang](https://www.jbang.dev) is a good option for teams that don't want to install Java or an IDE or worry about compiling code. This simple example uses the [Java Faker](https://github.com/DiUS/java-faker) library to generate a user name and then make an API call.

* [karate.java](karate.java) - this sets up Karate and adds the Java Faker dependency to the classpath
* [test.feature](test.feature) - a simple test that uses the Java Faker Java API to generate a person name

## Running
With JBang installed, this command will run the tests. You can replace `.` with any valid path that contains `*.feature` files.

```
jbang karate.java .
```

## Further Reading

* [Other Runtime Options](https://github.com/karatelabs/karate/wiki/Get-Started:-Other-Runtime-Options)





