FROM openjdk:17-alpine
WORKDIR /app
RUN chmod a+x /app/
COPY transport/target/transport-0.0.1-SNAPSHOT.jar /app/transport-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/transport-0.0.1-SNAPSHOT.jar"]

