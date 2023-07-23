package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.security.CustomAuthenticationFilter;
import com.example.demo.security.SimpleAuthenticationManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http	
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationManager(new SimpleAuthenticationManager())
		.addFilterBefore(new CustomAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers(AntPathRequestMatcher.antMatcher("api/**")).authenticated()
			.anyRequest().permitAll()
		);

		return http.build();
	}
}
