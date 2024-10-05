# IT-Ranks-Assessment

This project is a demonstration of a microservices architecture built using Spring Boot. It includes several services for handling authentication, configuration, service discovery, and employee management.

## Services

1. **Config Server**:
   - Centralized configuration management for all services.

2. **Gateway Service**:
   - Acts as an API gateway, routing requests to the appropriate services and managing JWT authentication.
   - **Runs on port**: **8883**
     
3. **Discovery Service**:
   - Uses Eureka for service discovery, enabling services to find each other.

4. **Auth Service**: 
   - Handles user authentication and provides an endpoint for obtaining an access token.
   - To get the access token, hit `/auth/login` with the following credentials:
     - **Email**: `test@gmail.com`
     - **Password**: `password`
       
5. **Employee Service**:
   - Provides CRUD operations for employee management and is secured with JWT (JSON Web Tokens).
   - **Database**: Utilizes PostgreSQL.


## Technologies Used

- Spring Boot
- Hibernate
- Spring Cloud (2023)
- PostgreSQL
- JWT (JSON Web Tokens)

## Postman Documentation

- https://documenter.getpostman.com/view/24751573/2sAXxMftGP
