package com.ccostao.projectstoreapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ccostao.projectstoreapi.service.DBService;

@Configuration
@Profile("test")
public class TestConfigDB {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws Exception {
		dbService.instantiateTestDatabase();
		return true;
	}
	
}
