FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY . /app
# COPY /taget/swagger_docs-1.0.0.jar app.jar
RUN ["cd", "app", "./mvnw", "clean", "install"]
RUN ls -a /app
# ENTRYPOINT [ "java", "-jar", "app.jar" ]
