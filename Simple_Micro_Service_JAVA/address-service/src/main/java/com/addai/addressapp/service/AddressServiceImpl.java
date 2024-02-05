package com.addai.addressapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.addai.addressapp.model.Address;
import com.addai.addressapp.repository.AddressRepository;
import com.addai.addressapp.response.AddressResponse;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<String> addAddress(Address address){
		addressRepo.save(address);
		return ResponseEntity.status(HttpStatus.CREATED).body("Address addded");
	}
	
	@Override
	public AddressResponse findAddressById(int id) {
		Optional<Address> optional = addressRepo.findById(id);
		if (!optional.isPresent()) {
			return null;
		}
		Address address = optional.get();
		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		
		return addressResponse;
	}

	@Override
	public ResponseEntity<String> editAddressById(int id, Address address) {
		Address existingAddress = addressRepo.findById(id).get();
		existingAddress.setCity(address.getCity());
		existingAddress.setCountry(address.getCountry());
		existingAddress.setProvince(address.getProvince());
		existingAddress.setStreet(address.getStreet());
		existingAddress.setZip(address.getZip());
		existingAddress.setCustomerId(address.getCustomerId());
		addressRepo.save(existingAddress);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Address updated");
	}

	@Override
	public ResponseEntity<String> deleteAddressById(int id) {
		addressRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Address deleted");
	}

//	@Override
//	public List<Address> findAllAddresses() {
//		List<Address> address = addressRepo.findAll();
////		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
//		return address;
//	}
	
	@Override
	public List<AddressResponse> findAllAddresses() {
		List<Address> address = addressRepo.findAll();
		AddressResponse addressResponse = new AddressResponse();
		List<AddressResponse> response = new ArrayList<>();
		for (Address a : address) {
			addressResponse = modelMapper.map(a, AddressResponse.class);
//			BeanUtils.copyProperties(a, addressResponse);
			response.add(addressResponse);
		}
		
		return response;
	}

	
}
