FROM maven:3.9-eclipse-temurin-21-alpine AS build

# RUN apt-get update
# RUN apt-get install openjdk-21-jdk -y

COPY . .

RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build ./taget/swagger_docs-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
