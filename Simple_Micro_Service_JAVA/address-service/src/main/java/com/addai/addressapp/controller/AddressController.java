package com.addai.addressapp.controller;

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

import com.addai.addressapp.model.Address;
import com.addai.addressapp.response.AddressResponse;
import com.addai.addressapp.service.AddressServiceImpl;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	
	@Autowired
	private AddressServiceImpl addressService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addAddress(@RequestBody Address address){
		return addressService.addAddress(address);
	}
	
	@GetMapping
	public List<AddressResponse> findAllAddresses(){
		return addressService.findAllAddresses();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> findCustomerAddressById(@PathVariable("id") int id){
		AddressResponse addressResponse = addressService.findAddressById(id);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editCustomerAddressById(int id, Address address){
		return addressService.editAddressById(id, address);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomerAddressById(int id) {
		return addressService.deleteAddressById(id);
	}

}
