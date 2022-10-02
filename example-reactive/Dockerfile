FROM amazoncorretto:11.0.16-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/example-reactive-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
