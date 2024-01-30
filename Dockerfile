FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . /app

RUN apt-get install maven -y
RUN cd app && mvn clean install

EXPOSE 8080

COPY --from=build /app/taget/swagger_docs-1.0.0.war /app/app.war

ENTRYPOINT [ "java", "-jar", "app/", "app.jar" ]
