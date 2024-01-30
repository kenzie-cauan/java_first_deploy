FROM maven:3.9-eclipse-temurin-21-alpine AS build
RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY . .
COPY /taget/swagger_docs-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
