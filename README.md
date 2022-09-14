#BookMyShow
This repository is created as part of demo app for a tech interview

### Getting Started
This service is build on SpringBoot v2.7.3(stable LTS) running on JDK 11

### Dependencies
- SpringBoot starter web & test
- SpringBoot actuator
- SpringBoot AOP
- Redis om spring
- jrediseach
- logback-JSON format
- springdoc open api
- lombok

### Ports
- 9090 - the app runs on this port and this is same as the apps management port
- 9091 - the test runs on this port

### Health Check
- Overall health check status at - {host}:9090/bookmyshow/manage/health
- liveness probe status at - {host}:9090/bookmyshow/manage/health/liveness
- readiness probe status at  - {host}:9090/bookmyshow/manage/health/readiness

### Swagger
This application uses SpringDoc, which internally uses OpenAPI standards and compiles a UI in swagger v3 format
The swagger can be accessed at {host}:9090/bookmyshow/swagger-ui.html

### APIs
There is a separate controller for B2B and B2C features
- #### in B2B
  1. Create a partner - 
  PUT {host}:9090/bookmyshow/v1/b2b/partner
  2. Update screening details for existing partner - 
  PUT {host}:9090/bookmyshow/partner/{partner id}/movie
  3. Get all partner details - 
  GET {host}:9090/bookmyshow/v1/b2b/partner
  4. Get a particular partner details by id - 
  GET {host}:9090/bookmyshow/v1/b2b/partner/{partner id}
  5. Delete partner by id - 
  DELETE {host}:9090/bookmyshow/v1/b2b/partner/{id}
- #### in B2C
  1. Create a user account - 
  PUT {host}:9090/bookmyshow/v1/b2c/user
  2. Book tickets - 
  POST {host}:9090/bookmyshow/v1/b2c/user/{user id}/book
  3. Search movie by city - 
  GET {host}:9090/bookmyshow/v1/b2c/search?city=Bangalore&movie=ABC
  4. Get all bookings - 
  GET {host}:9090/bookmyshow/v1/b2c/bookings
  5. Delete a booking - 
  DELETE {host}:9090/bookmyshow/v1/b2c/booking/{booking id}

### Tests
A sample test is written using SpringBootTest and Auto configured mock mvc. 
The repository is mocked.

