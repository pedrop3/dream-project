FROM maven:3.8.7-amazoncorretto-17 AS builder

ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

RUN mkdir -p /app
WORKDIR /app

COPY pom.xml /app

COPY src /app/src

RUN mvn clean package

FROM amazoncorretto:17.0.5

EXPOSE 9040

COPY --from=0 /app/target/*.jar /app/app.jar