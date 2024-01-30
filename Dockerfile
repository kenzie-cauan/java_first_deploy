FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY . /app

RUN ["cd", "/app"]
RUN ["./mvnw", "clean", "install"]

# ENTRYPOINT [ "java", "-jar", "app.jar" ]
