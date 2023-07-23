package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class CustomAuthenticationFilter extends GenericFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735811127449620022L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String username = ((HttpServletRequest) request).getHeader("custom-authorization");
		if (StringUtils.hasText(username)) {
			if (username.startsWith("d")) {
				SecurityContextHolder.getContext().setAuthentication(new SimpleAuthenticationHolder(username, new SimpleGrantedAuthority("ROLE_DELETER")));
			} else {
				SecurityContextHolder.getContext().setAuthentication(new SimpleAuthenticationHolder(username));
			}
		}
		chain.doFilter(request, response);
	}

}
