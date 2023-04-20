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

