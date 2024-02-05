package com.addai.customerapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.addai.customerapp.fallback.CustomerServiceFallback;
import com.addai.customerapp.module.Customer;
import com.addai.customerapp.repository.CustomerRepository;
import com.addai.customerapp.response.AddressResponse;
import com.addai.customerapp.response.CustomerResponse;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void addNewCustomer(Customer customer) {
		customerRepo.save(customer);
	}
	
//	@Override
//	public List<Customer> findAllCustomers(){
//		return customerRepo.findAll();
//	}
	
	
	@Override
	public ResponseEntity<CustomerResponse> findCustomerById(int id) {
		Optional<Customer> optional = customerRepo.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		Customer customer = optional.get();
		CustomerResponse customerResponse = new CustomerResponse();
//		CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
		BeanUtils.copyProperties(customer, customerResponse);
		AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/addresses/{id}", AddressResponse.class, id);
		customerResponse.setAddressResponse(addressResponse);
		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
	}

	
//	@Override
//	public List<CustomerResponse> findAllCustomersAndAddress() {		
//		List<Customer> customer = customerRepo.findAll();
//		List<CustomerResponse> response = new ArrayList<>();
//		CustomerResponse customerResponse = new CustomerResponse();		
//		AddressResponse[] addressResponse = restTemplate.getForObject("http://localhost:8081/addresses", AddressResponse[].class);
//		for (Customer c : customer) {
//			for (AddressResponse a : addressResponse) {
//				if (c.getId() == a.getCustomerId()) {
//					customerResponse = modelMapper.map(c, CustomerResponse.class);
//					customerResponse.setAddressResponse(a);
//					response.add(customerResponse);
//				}
//			}			
//		}
//		
//		return response;
//	}
	
	@Override
	public List<CustomerResponse> findAllCustomers() {		
		List<Customer> customer = customerRepo.findAll();
		List<CustomerResponse> response = new ArrayList<>();
		CustomerResponse customerResponse = new CustomerResponse();		
		AddressResponse[] addressResponse = restTemplate.getForObject("http://address-service/addresses", AddressResponse[].class);
		for (Customer c : customer) {
			for (AddressResponse a : addressResponse) {
				if (c.getId() == a.getCustomerId()) {
					customerResponse = modelMapper.map(c, CustomerResponse.class);
					customerResponse.setAddressResponse(a);
					response.add(customerResponse);
				}
			}			
		}
		
		return response;
	}
	
	@Override
	public ResponseEntity<String> editCustomerById(int id, Customer customer) {		
		Optional<Customer> optional = customerRepo.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}
		Customer existingCustomer = optional.get();
		existingCustomer.setName(customer.getName());
		existingCustomer.setCategory(customer.getCategory());
		existingCustomer.setEmail(customer.getEmail());
		customerRepo.save(existingCustomer);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer updated successfully");
	}

	@Override
	public ResponseEntity<String> deleteCustomerById(int id) {
		boolean existed = customerRepo.existsById(id);
		if (!existed) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}
		customerRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Customer deleted!");
	}
	
	// fallback method
//	@Override
//	public ResponseEntity<CustomerResponse> customerFallback(int id, Exception e) {
//		Customer customer = customerRepo.findById(id).get();
//		CustomerResponse customerResponse = new CustomerResponse();
//		BeanUtils.copyProperties(customer, customerResponse);
//		customerResponse.setAddressResponse(null);
//		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
//	}
	
//	@Override
//	public ResponseEntity<CustomerResponse> customerFallback(Exception e) {		
//		CustomerResponse customerResponse = new CustomerResponse();
//		customerResponse.setId(0);
//		customerResponse.setName("Default Customer");
//		customerResponse.setCategory("A");
//		customerResponse.setEmail("defaultcustomer@gmail.com");
//		customerResponse.setAddressResponse(null);
//		return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
//	}

	// fallback method
	@Override
	public ResponseEntity<CustomerServiceFallback> customerServiceFallback(Exception e) {
		CustomerServiceFallback cutomerFallback = new CustomerServiceFallback("ERROR", "Could not reach the address system.", "Address service temporary busy or down.", HttpStatus.NOT_FOUND );
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cutomerFallback);
	}

	
}
