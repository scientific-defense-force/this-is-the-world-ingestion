FROM openjdk:8u131-jdk

RUN  apt-get update \
  && apt-get install -y \
    apt-transport-https \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* /var/tmp/*

ENV SCALA_VERSION 2.12.2

RUN FILE="scala-$SCALA_VERSION.deb"; \
    wget "https://downloads.lightbend.com/scala/$SCALA_VERSION/$FILE" -qO $FILE \
    && dpkg -i $FILE \
    && rm $FILE

RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list \
    && apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823

ENV SBT_VERSION 0.13.15

RUN apt-get update \
  && apt-get install -y sbt=$SBT_VERSION \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* /var/tmp/*
