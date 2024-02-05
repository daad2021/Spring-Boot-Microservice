package com.addai.customerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addai.customerapp.fallback.CustomerServiceFallback;
import com.addai.customerapp.module.Customer;
import com.addai.customerapp.response.CustomerResponse;
import com.addai.customerapp.service.CustomerServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	public static final String CUSTOMER_SERVICE = "customer-service";
	
	@PostMapping("/add")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
		customerService.addNewCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
	}
	
//	@GetMapping
//	public List<Customer> getAllCutomers(){
//		return customerService.findAllCustomers();
//	}
	
	
//	@GetMapping("/address")
//	public List<CustomerResponse> getAllCutomersAddress(){
//		return customerService.findAllCustomersAndAddress();
//	}
	
	@GetMapping
	public List<CustomerResponse> getAllCutomers(){
		return customerService.findAllCustomers();
	}
	
	@GetMapping("/{id}")
	@CircuitBreaker(name = CUSTOMER_SERVICE, fallbackMethod = "customerServiceFallback")
	public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("id") int id) {
		return  customerService.findCustomerById(id);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
		return customerService.editCustomerById(id, customer);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer updated successfully");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") int id) {
		return customerService.deleteCustomerById(id);
	}
	
	// customer service fallback method
	public ResponseEntity<CustomerServiceFallback> customerServiceFallback(Exception e){
		return customerService.customerServiceFallback(e);
	}
}
