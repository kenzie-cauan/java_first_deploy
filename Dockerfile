FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY . /app

# RUN ls -a /app
RUN ["cd", "./app"]
RUN ["./mvnw", "clean", "install"]

# COPY /taget/swagger_docs-1.0.0.jar app.jar

# ENTRYPOINT [ "java", "-jar", "app.jar" ]
