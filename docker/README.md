# Karate and Docker - Standalone JAR

This simple minimalistic [`Dockerfile`](Dockerfile) is sufficient to package a Java Runtime Environment and the Karate [Standalone JAR](https://github.com/karatelabs/karate/wiki/Get-Started:-Other-Runtime-Options#standalone-jar).

Here we are using an [`adoptopenjdk`](https://hub.docker.com/_/adoptopenjdk) Docker image as a base.

```docker
FROM adoptopenjdk:11-jre-hotspot

RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*

RUN wget -O karate.jar https://github.com/karatelabs/karate/releases/download/v1.4.0/karate-1.4.0.jar
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

## Further Reading

* [Get Started - Other Runtime Options](https://github.com/karatelabs/karate/wiki/Get-Started:-Other-Runtime-Options#docker)
* There are some tips, tricks and recipes in this thread: [https://github.com/karatelabs/karate/issues/396](https://github.com/karatelabs/karate/issues/396)