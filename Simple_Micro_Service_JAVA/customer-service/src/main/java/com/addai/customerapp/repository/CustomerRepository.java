package com.addai.customerapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addai.customerapp.module.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
//	@Query(value="SELECT c.id, c.name, c.email, a.street, a.zip, a.city, a.country FROM customerdb.customer c JOIN addressdb.address a ON c.id=a.customer_id WHERE c.id=:id;", nativeQuery=true)
//	public Optional<Customer> findCustomerAddressById(int id);	
	
	@Query(value="SELECT c.id, c.name, c.category, c.email, a.street, a.zip, a.city, a.country FROM customerdb.customer c JOIN addressdb.address a ON c.id=a.customer_id;", nativeQuery=true)
	public List<Customer> findAllCustomersAddress();	

}
