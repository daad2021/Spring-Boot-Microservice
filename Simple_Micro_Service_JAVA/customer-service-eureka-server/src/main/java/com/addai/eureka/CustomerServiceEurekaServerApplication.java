package com.addai.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CustomerServiceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceEurekaServerApplication.class, args);
		
		System.out.println("Customer services eureka server started");
	}

}
