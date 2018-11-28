package com.simonis.reactive;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public class ReactiveUserDetailsImpl 
implements ReactiveUserDetailsService {

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		// TODO Auto-generated method stub
		return Mono.justOrEmpty("petra".equals(username) 
				? User.withUsername("petra").build():null);
	}

}
