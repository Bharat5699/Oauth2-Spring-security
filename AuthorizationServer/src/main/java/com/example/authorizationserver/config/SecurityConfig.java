package com.example.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
          authorizeRequests.anyRequest().authenticated()
        )
          .formLogin();
        return http.build();
    }
	
	@Bean
	UserDetailsService users() {
	    UserDetails user = User.withUsername("admin")
	      .password(passwordEncoder().encode("password"))
	      .roles("NORMAL")
	      .build();
	    return new InMemoryUserDetailsManager(user);
	}
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	
	
}
