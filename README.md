# GitHub Repo Service

## Overview
```markdown
# RestService

## Overview
RestService is a Spring Boot application that provides RESTful web services. It includes functionalities to fetch GitHub repository owner details.

## Prerequisites
- Java 21 or higher
RestService is a Spring Boot application that provides RESTful web services. It includes functionalities to fetch GitHub repository owner details.

- Maven 3.8.1 or higher

## Prerequisites
- Java 21 or higher
## Configuration
- Maven 3.8.1 or higher

## Run Commands

build : mvn clean install

  Run : Spring Boot Run: java -jar restService-0.0.1-SNAPSHOT.jar

## Swagger Documentation

  http://localhost:8080/swagger-ui/index.html
 

## Deployment 
 
    
    Docker : 
  
          docker build -t restService:latest .
          
          docker push mahenmsd/restService:latest
          
          docker run -p 8080:8080 -d --name restService mahenmsd/restService:latest
