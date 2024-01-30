FROM ubuntu:latest

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . /app

RUN apt-get install maven -y
RUN cd app && mvn clean install

EXPOSE 8080

RUN cp -r /app/target/swagger_docs-1.0.0.war /app/app.war

ENTRYPOINT [ "java", "-war", "/app/app.war"]
