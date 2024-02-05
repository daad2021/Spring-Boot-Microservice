package com.addai.addressapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.addai.addressapp.model.Address;
import com.addai.addressapp.response.AddressResponse;

public interface AddressService {

	public ResponseEntity<String> addAddress(Address address);
	
	public ResponseEntity<String> editAddressById(int id, Address address);
	
	public ResponseEntity<String> deleteAddressById(int id);
	
	public List<AddressResponse> findAllAddresses();
	
	public AddressResponse findAddressById(int id);
}
