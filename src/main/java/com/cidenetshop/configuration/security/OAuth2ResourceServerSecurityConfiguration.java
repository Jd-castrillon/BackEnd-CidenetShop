package com.cidenetshop.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerSecurityConfiguration extends ResourceServerConfigurerAdapter {

	public OAuth2ResourceServerSecurityConfiguration() {
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.cors();

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/unauthenticated/**").permitAll().anyRequest().permitAll();
	}

}
