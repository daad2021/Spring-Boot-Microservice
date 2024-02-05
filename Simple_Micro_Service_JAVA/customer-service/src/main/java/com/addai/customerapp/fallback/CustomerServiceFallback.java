package com.addai.customerapp.fallback;

import org.springframework.http.HttpStatus;

public class CustomerServiceFallback {
	
	private String error;
	private String message;
	private String cause;
	private HttpStatus status;
	
	public CustomerServiceFallback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerServiceFallback(String error, String message, String cause, HttpStatus status) {
		super();
		this.error = error;
		this.message = message;
		this.cause = cause;
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
