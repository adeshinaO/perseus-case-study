## Overview

A Simple REST service with endpoints for CRUD operations.

#### Technology Stack

- Java 11
- Maven
- Spring Boot
- H2 In-memory Database
- Docker

#### How to Deploy Locally

Open a terminal at the project root and do the following:

- Run all tests and build an executable JAR
```shell
    ./mvnw clean package 
```

- Create an image using the Dockerfile in the project root
```shell
    docker build --tag=perseus-api:latest .
```

- Start a Docker container with port 8080 exposed
```shell
    docker run -p8080:8080 perseus-api:latest
```

#### API Documentation  

To view the documentation for API endpoints, deploy the service and go to http://localhost:8080/docs.html on a browser

NOTE: The web console for the embedded H2 database is available at http://localhost:8080/h2-console
