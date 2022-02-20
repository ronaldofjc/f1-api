FROM openjdk:17.0-jdk-slim

VOLUME /tmp
ARG JAR_FILE=./target/f1-api-0.0.1-SNAPSHOT.jar
ARG PROFILE=dev
ENV PROFILE=${PROFILE}
ENV JAR_FILE=${JAR_FILE}

COPY $JAR_FILE /opt/app.jar

ENTRYPOINT exec java -jar -Dspring.profiles.active=$PROFILE /opt/app.jar
