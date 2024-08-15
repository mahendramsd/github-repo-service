# RestService

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

The application is configured using `application.yml` file located in `src/main/resources`. Below is a sample configuration:

```yaml
spring:
## Configuration
  application:
    name: restService

The application is configured using `application.yml` file located in `src/main/resources`. Below is a sample configuration:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
  application:
    name: restService

    driver-class-name: org.h2.Driver
    username: sa
  datasource:
    url: jdbc:h2:mem:testdb
    password: password
    jpa:
      hibernate:
    driver-class-name: org.h2.Driver
    username: sa
        ddl-auto: create-drop
      show-sql: true
      properties:
        hibernate:
    password: password
    jpa:
      hibernate:
        ddl-auto: create-drop
          dialect: org.hibernate.dialect.H2Dialect

  h2:
    console:
      show-sql: true
      properties:
        hibernate:
      enabled: true

server:
  port: 8080

logging:
  level:
          dialect: org.hibernate.dialect.H2Dialect

  h2:
    console:
    root: info
    com.msd.restservice: debug
      enabled: true

server:
  port: 8080

logging:
  level:
  pattern:
    root: info
    com.msd.restservice: debug
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
  file:
    name: logs/restservice.log
