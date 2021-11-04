package com.cidenetshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
@EnableResourceServer
@SpringBootApplication
public class CidenetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CidenetShopApplication.class, args);
	}

}
