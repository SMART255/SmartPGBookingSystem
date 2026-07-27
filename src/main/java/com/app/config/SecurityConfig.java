package com.app.config;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            
            .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))


            .authorizeHttpRequests(auth -> auth

                    // Public APIs
                    .requestMatchers(
                            "/user/register",
                            "/auth/login"
                    ).permitAll()

                    // USER APIs
                    .requestMatchers("/user/**")
                    .hasRole("USER")

                    // OWNER APIs
                    .requestMatchers("/owner/**")
                    .hasRole("OWNER")

                    // ADMIN APIs
                    .requestMatchers("/admin/**")
                    .hasRole("ADMIN")

                    .anyRequest()
                    .authenticated()
            )

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}