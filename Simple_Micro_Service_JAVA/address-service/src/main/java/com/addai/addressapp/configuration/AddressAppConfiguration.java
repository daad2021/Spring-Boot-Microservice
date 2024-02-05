package com.addai.addressapp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressAppConfiguration {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
