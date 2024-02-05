# Spring-Boot-Microservice
This is a simple Java Spring boot REST API project built by following the microservice architecture. This project can be helpful to beginners for learning and practice.
Customer service communicate with Address service through RestTemplate to fetch the customer address.
The operations/functions implemented are POST, GET, PUT and DELETE.

Modules:
	1. Customer service
	2. Address service
	3. Service Discovery service(Eureka server)
	4. API Gateway
	5. Circuit breaker(Resilience4j)

The service are registered with the eureka server.
The circuit breaker monitors the status of the services, controls the requests that are sent to the Address service,
executing a fallback method to return a default response to the requesting service(Customer service) in the case of service unvailability.
The API gateway is used to achieve a single point of contact for the services.

SAMPLE RESPONSE:
![Response_1](https://github.com/daad2021/Spring-Boot-Microservice/assets/84599965/523be21b-6b33-4096-8f94-ad76175a8a48)
![Response_2](https://github.com/daad2021/Spring-Boot-Microservice/assets/84599965/d7865159-7538-45f8-b85f-1ba1447f339e)
