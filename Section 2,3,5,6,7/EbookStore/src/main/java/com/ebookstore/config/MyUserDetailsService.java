package com.ebookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ebookstore.app.Person;
import com.ebookstore.app.PersonRepository;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	PersonRepository personrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person p = personrepository.findByUsername(username);

		if (p == null)
			throw new UsernameNotFoundException(username);
		else
			return new MyUserPrincipal(p);
	}

}
