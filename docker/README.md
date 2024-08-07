# Karate and Docker

## Standalone JAR
This simple minimalistic [`Dockerfile`](Dockerfile) is sufficient to package a Java Runtime Environment and the Karate [Standalone JAR](https://github.com/karatelabs/karate/wiki/Get-Started:-Other-Runtime-Options#standalone-jar).

Here we are using an [`eclipse-temurin`](https://hub.docker.com/_/eclipse-temurin/) Docker image as a base.

```docker
FROM eclipse-temurin:17-jre

RUN curl -L -o karate.jar https://github.com/karatelabs/karate/releases/download/v1.5.0/karate-1.5.0.jar
```

The Docker recipe is very simple, just download `karate.jar` into the root of the docker image. To build a docker image called `karate-jre` locally, you can do this: 

```bash
docker build -t karate-jre .
```

Now to run a set of Karate tests in a `src` folder within the current directory (outside the docker image) you can do this:

```bash
 docker run -it --rm -v "$(pwd)/src":/src -w /src karate-jre java -jar /karate.jar .
```

If you are on Windows, [refer to this](https://stackoverflow.com/a/41489151/143475) for equivalents of the `$(pwd)` etc.

The explanation of the above command is as follows:

* `-it` runs in interactive mode, and `--rm` removes the temporary image after use
* use `-v` to mount the `./src` folder as `/src`.
* use `-w` to make `/src` the working directory
* now the command `java -jar /karate.jar .` will run all feature files in the current folder (which is `.`)
* note that test reports will appear in `./src/target`

All the possible Karate command-line options are explained here: [Usage](https://karatelabs.github.io/karate/karate-netty/#usage).

You can easily customize the above recipe, for example you could bundle your tests within the docker image. One nice thing about the above example is that the test reports are created outside the image, so you can view them even after the docker process stops.

## Adding JARs to the classpath

This [Dockerfile](https://github.com/karatelabs/karate-todo/blob/main/cfg/Dockerfile-app) which is part of the [karate-todo](https://github.com/karatelabs/karate-todo) example shows how you can add JAR files to the classpath (from a Maven build), and run a java command when the container starts. This pattern is useful if you want to "ship" a Docker image that embeds a [Karate mock](https://karatelabs.github.io/karate/karate-netty).

You can also `ADD` feature files to the Docker image and run tests when the container starts. In this case, mounting a `/target` directory may be needed to see reports.

## Using Maven

Using the [maven:3-amazoncorretto-17](https://hub.docker.com/layers/library/maven/3-amazoncorretto-17/images/sha256-9c29d34f56dcb979fb5819193abff63246115b21cdfc58287f73708af7a66117?context=explore) image may be sufficient for most use-cases. For example:

```bash
docker run -it --rm -v "$(pwd)":/src -w /src -v "$HOME/.m2":/root/.m2 maven:3-amazoncorretto-17 mvn test
```

Here we use `-v "$HOME/.m2":/root/.m2` to mount and re-use your local Maven JAR download "cache" (which saves time), but you can omit it if needed for a true "from scratch" experience.

## Further Reading

* [Get Started - Other Runtime Options](https://github.com/karatelabs/karate/wiki/Get-Started:-Other-Runtime-Options#docker)
* There are some tips, tricks and recipes in this thread: [https://github.com/karatelabs/karate/issues/396](https://github.com/karatelabs/karate/issues/396)