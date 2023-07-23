package com.example.demo.security;

import java.util.Arrays;
import java.util.Collection;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SimpleAuthenticationHolder implements Authentication {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6902437988937633546L;
	private final String name;
	private final Collection<? extends GrantedAuthority> roles;
	protected <T extends GrantedAuthority> SimpleAuthenticationHolder(String name, T... roles) {
		this.name = name;
		this.roles = Arrays.asList(roles);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public Object getCredentials() {
		return this.name;
	}

	@Override
	public Object getDetails() {
		return this.name;
	}

	@Override
	public Object getPrincipal() {
		return this.name;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
