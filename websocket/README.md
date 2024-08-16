# Karate Websocket

Karate has native support for [the Websocket API](https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API) as an optional dependency (non-open source and commercial). Enterprise users can find more information here: [Karate-Websocket](https://github.com/karatelabs/karate-addons/tree/main/karate-websocket). 

A license is required for running (for e.g. in CI/CD) and a Karate Labs IDE subscription is required per developer seat.

This sample project shows how to handle the async nature of Websocket communication and handle a custom protocol such as [STOMP](https://stomp.github.io/). Make sure you have a `.karate/karate.lic` file in place before running the example.

## Running

This is a normal Java / Maven project. To run the simple [`echo.feature`](src/test/java/karate/echo.feature) you can do: `mvn clean test`.

There is also [`json.feature`](src/test/java/karate/json.feature) that can be run as follows: `mvn test -Dtest=JsonRunner`. This shows how the built-in `io.karatelabs.websocket.JsonAdapter` can be used if all messages are known to be JSON.

There is an advanced example: [`stomp.feature`](src/test/java/karate/stomp.feature) that shows how to fully control the flow of messages and the conversion of text (or bytes) to and from JSON in the test. Concepts such as how to implement a message adapter i.e. [`StompAdapter.java`](src/test/java/karate/StompAdapter.java) are explained in the [documentation](https://github.com/karatelabs/karate-addons/tree/main/karate-websocket).

To run the STOMP example, you need to download and start this Spring-Boot based project: [gs-messaging-stomp-websocket
](https://github.com/spring-guides/gs-messaging-stomp-websocket). The [`/complete`](https://github.com/spring-guides/gs-messaging-stomp-websocket/tree/main/complete) directory has a ready to run Maven project which you can start from the command-line like this:

```
mvn exec:java -Dexec.mainClass="com.example.messagingstompwebsocket.MessagingStompWebsocketApplication"
```

You can also open the `complete` project within a Java IDE and run the main class referred in the command above as an alternative.

This will start a local web-app on port 8080 and you can also view a demo web-page at `http://localhost:8080`. Now you can run the Karate test that communicates to the just-started server via STOMP over Websocket like this:

```
mvn test -Dtest=StompRunner
```

## Further Reading

Karate has had very basic support for websockets in the open-source version which may suffice for some teams. The following information is available:

* [Documentation](https://github.com/karatelabs/karate#websocket)
* Examples:
  * [echo.feature](https://github.com/karatelabs/karate/blob/master/karate-demo/src/test/java/demo/websocket/echo.feature)
  * [websocket.feature](https://github.com/karatelabs/karate/blob/master/karate-demo/src/test/java/demo/websocket/websocket.feature)
* [Karate and WebSockets on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+websocket)
* [Using a custom Java implementation if required](https://stackoverflow.com/a/69299321/143475)
