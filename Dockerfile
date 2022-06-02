FROM adoptopenjdk/openjdk11:latest
MAINTAINER Adeshina Ogunmodede
COPY /target/perseus-rest-api-1.0.0.jar perseus-rest-api-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/perseus-rest-api-1.0.0.jar"]
