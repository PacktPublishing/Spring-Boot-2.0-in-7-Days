package com.ebookstore.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.ebookstore.app.Person;

public class MyUserPrincipal implements UserDetails {

	private Person person;
	private User user;

	public MyUserPrincipal(Person p) {
		this.person = p;
		this.user = new User(p.getUsername(), p.getPassword(), getAuthorities());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = Collections
				.singletonList(new SimpleGrantedAuthority(person.getUserRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return person.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return person.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Person getPerson() {
		return person;
	}

	public User getUser() {
		return user;
	}

}
