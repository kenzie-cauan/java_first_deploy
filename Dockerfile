FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21 -y
COPY . /app

RUN apt-get install maven -y
RUN mvn clean install

RUN ls -a
# FROM openjdk:21-jdk-slim


# EXPOSE 8080

# COPY --from=build /taget/swagger_docs-1.0.0.war app.war

# ENTRYPOINT [ "java", "-jar", "app.jar" ]
