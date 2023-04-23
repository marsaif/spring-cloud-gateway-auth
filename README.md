## Microservices Gateway with JWT and Role-based Authorization

This is a sample project that demonstrates how to implement a microservices gateway using Spring Cloud Gateway and Spring Security. 
The gateway provides a secure entry point to your microservices architecture, and implements JWT-based authentication and role-based authorization.
The project consists of the following components:

* Gateway Service: 
The Spring Cloud Gateway service, which acts as the entry point to your microservices architecture. 
The gateway is implemented using Spring Cloud Gateway, and provides routing and security capabilities.
The gateway service runs on port 8000.

* Eureka Service: The Spring Cloud Eureka service, 
which provides service registration and discovery capabilities for your microservices architecture. 
The Eureka service helps the gateway to locate and route requests to the appropriate microservice instance.
The Eureka service runs on port 8761.

* Book Service: A sample microservice that provides book-related APIs. 
The service is implemented using Spring Boot, and is secured using JWT-based authentication and role-based authorization.
The book service runs on port 8002.

* Author Service: A sample microservice that provides author-related APIs. 
The service is implemented using Spring Boot, and is secured using JWT-based authentication and role-based authorization.
The author service runs on port 8001. 

## Security Configuration
To configure the JWT authentication and authorization, edit the application.yml file in the gateway directory:
```
security:
  jwt:
    secret-key: your-secret-key
    expiration: 86400000 # 86400000 # a day
```
You can change the secret-key value to a random string of your choice. You can also change the expiration value to set the validity duration of the JWT token.

## Route Configuration
```
spring:
  cloud:
    gateway:
      routes:
        - id: authors_route
          uri: http://localhost:8001/
          predicates:
            - Path=/authors/**
          metadata:
            allowedRoles: USER
          filters:
            - AuthenticationFilter
        - id: books_route
          uri: http://localhost:8002/
          predicates:
            - Path=/books/**
          metadata:
            allowedRoles: ADMIN
          filters:
            - AuthenticationFilter
```
The above configuration sets up two routes for the gateway: one for the author service and one for the book service. The allowedRoles metadata is used to specify the roles that are allowed to access each route, and the AuthenticationFilter is added to the filter chain to authenticate incoming requests.

## Gateway Configuration

To restrict access to your microservices architecture, you can configure your microservices to only accept requests from the gateway service.
To configure the gateway address in your microservices, edit the application.yml file in the book and author directories:
```
gateway:
  address: 127.0.0.1
```
This configuration ensures that the microservices only accept requests from the gateway service at 127.0.0.1.
