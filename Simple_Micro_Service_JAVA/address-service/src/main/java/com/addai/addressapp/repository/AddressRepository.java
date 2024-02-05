package com.addai.addressapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addai.addressapp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
