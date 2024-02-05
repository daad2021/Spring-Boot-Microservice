package com.addai.customerapp.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="empId")
	private int id;
	
//	@Column(name="empName")
	private String name;
	
//	@Column(name="empCategory")
	private String category;
	
//	@Column(name="empEmail")
	private String email;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String category, String email) {
		super();
		this.name = name;
		this.category = category;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
