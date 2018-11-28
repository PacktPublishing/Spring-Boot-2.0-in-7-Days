package com.simonis.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {

	@Bean
	public ReactiveUserDetailsService userDetailsService() {
		return new MapReactiveUserDetailsService(
			User.withUsername("reactive")
				.password("{noop}boot")
				.roles("USER")
				.build()
		);
	}
}
