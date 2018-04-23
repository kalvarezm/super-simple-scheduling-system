# super-simple-scheduling-system
REST API to for a system that assigns students to classes.

## Description
Write-up around what technologies/frameworks you are/would use in implementing various parts/tiers of this system

It was used Java 8 with Spring ecosystem technologies as a core. 
With Spring Boot and the starter-web we have a core configuration and controller handling. 

The system is composed for some parts. Next a little description: 
- Controller: With @RestController, it exposes the endpoints for the Student and Class operation. We tried to align with good practiques and principles of RESTful. 
- Service: The business logic where we communicate with the controller tier. Additionally, we create a template for generic crud operations. 
- Model: The representation from the entities.
- Repository: JPA Implementation. It was used different techniques like @Query, QBE(Query by Example) and JPARepository methods. 
- Exception: It was created checked exceptions to handle some problems like a Notfound condition. It was used @ControllerAdvice to catch the exception and generalize their behavior.
- Documentation: It was used Swagger for the API documentation. Enter to: http://localhost:8090/s4/api/v1/swagger-ui.html#/
- Database: It was used H2
- Logging: It was used slf4j for print error messages. 

For deploy you can run main method in Application.java. 
- You can try entering to http://localhost:8090/s4/api/v1/students and then read the API documentation