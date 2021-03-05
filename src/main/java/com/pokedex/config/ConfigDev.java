package com.pokedex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pokedex.service.BDService;

@Configuration
@Profile("dev")
public class ConfigDev {
	
	@Autowired
	private BDService service;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;;
	
	@Bean
	public boolean initBD() {
		if(strategy.equals("create")) {
			this.service.initDataBase();
		}
		return false;
	}

}
