package com.addai.customerapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.addai.customerapp.fallback.CustomerServiceFallback;
import com.addai.customerapp.module.Customer;
import com.addai.customerapp.response.CustomerResponse;

public interface CustomerService {
	
	public void addNewCustomer(Customer customer);
	
//	public List<Customer> findAllCustomers();
	
	public List<CustomerResponse> findAllCustomers();
		
	public ResponseEntity<CustomerResponse> findCustomerById(int id);
	
	public ResponseEntity<String> editCustomerById(int id, Customer customer);
	
	public ResponseEntity<String> deleteCustomerById(int id);

//	public List<CustomerResponse> findAllCustomersAndAddress();
	
//	public ResponseEntity<CustomerResponse> customerFallback(Exception e);
	
	public ResponseEntity<CustomerServiceFallback> customerServiceFallback(Exception e);
}
