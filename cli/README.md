# Karate and CLI / Bash

Karate has excellent support for calling external processes via the command-line. You can block and wait for output or you can even fork a process on a separate thread and listen to the output from the test.

The best documentation is present in [this answer on Stack Overflow](https://stackoverflow.com/a/62911366/143475)

## Examples
* [fork.feature](https://github.com/karatelabs/karate/blob/master/karate-core/src/test/java/com/intuit/karate/core/fork.feature)
* [fork-listener.feature](https://github.com/karatelabs/karate/blob/master/karate-core/src/test/java/com/intuit/karate/core/fork-listener.feature)

## Further Reading

[Using cURL to simulate non-standard HTTP request](https://stackoverflow.com/a/64352676/143475)
[Karate and SSH](../ssh/README.md)