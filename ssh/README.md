# Karate and SSH

Using the [Jsch](https://github.com/mwiede/jsch) Java library it is possible to open an SSH connection from Karate and send commands. This project provides a starting point and can be easily extended to return output to Karate for assertions or performing conditional logic.

> Note that we use a fork of the popular [Jsch](http://www.jcraft.com/jsch) library which supports stricter security, needed by AWS EC2 instances for example.

A test can be very simple as seen in [ssh.feature](src/test/java/karate/ssh.feature).

Most of the work is handled by [`SshSession.java`](src/test/java/karate/SshSession.java). It takes care of opening a connection and returning an object (itself) that can be used to send command to the remote server.

If you need more functionality, you can use the Karate core [Command](https://github.com/karatelabs/karate/blob/master/karate-core/src/main/java/com/intuit/karate/shell/Command.java) and [Console](https://github.com/karatelabs/karate/blob/master/karate-core/src/main/java/com/intuit/karate/shell/Console.java) classes as a reference to run a background thread, collect server output and make it available to the Karate / calling script.

Please do consider improving this example and contributing code back to this project !

## Further Reading
[Karate and CLI / Bash](../cli/README.md)