FROM adoptopenjdk:11-jre-hotspot

RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*

RUN wget -O karate.jar https://github.com/karatelabs/karate/releases/download/v1.4.0/karate-1.4.0.jar
