FROM ubuntu:22.04 as BUILD

RUN apt-get update && apt-get install \
    wget \
    maven \
    gcc \
    zlib1g-dev \
    build-essential \
    -y

RUN wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.1.0/graalvm-ce-java17-linux-amd64-22.1.0.tar.gz \
    && tar -xvzf graalvm-ce-java17-linux-amd64-22.1.0.tar.gz \
    && mv graalvm-ce-java17-22.1.0/ /usr/lib/jvm \
    && ln -s -f /usr/lib/jvm/graalvm-ce-java17-22.1.0/bin/java /usr/bin/java \
    && /usr/lib/jvm/graalvm-ce-java17-22.1.0/bin/gu install native-image

WORKDIR /work
COPY pom.xml /work
COPY src /work/src

RUN mvn package -Pnative


FROM ubuntu:22.04

WORKDIR /run
COPY --from=BUILD /work/target/horner-reader-1.0.0-SNAPSHOT-runner /run/app

EXPOSE 8080

ENTRYPOINT /run/app


