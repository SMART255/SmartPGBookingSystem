package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

						// Authentication
						.requestMatchers("/auth/**").permitAll()

						// User
						.requestMatchers("/user/**").permitAll()

						// Owner
						.requestMatchers("/owner/**").permitAll()

						// PG
						.requestMatchers("/pg/**").permitAll()

						// Room
						.requestMatchers("/room/**").permitAll()

						// Booking
						.requestMatchers("/booking/**").permitAll()

						// Payment
						.requestMatchers("/payment/**").permitAll()

						// Review
						.requestMatchers("/review/**").permitAll()

						// Document
						.requestMatchers("/document/**").permitAll()
						
						// Amenity
						.requestMatchers("/amenity/**").permitAll()

						// Swagger (optional)
						/*
						 * .requestMatchers( "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"
						 * ).permitAll()
						 */

						// Everything else
						.anyRequest().authenticated())

				.httpBasic(Customizer.withDefaults());

		// JWT Filter
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}
}