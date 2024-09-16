package com.springsecurity.Springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class StudentConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails student = User.withUsername("student")
                .password(encoder.encode("student123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, student);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.GET, "/studentdetails").hasAnyRole("ADMIN", "USER") // Allow GET for both ADMIN and USER roles
                .requestMatchers(HttpMethod.POST, "/studentdetails").hasRole("ADMIN") // Only ADMIN can POST
                .requestMatchers(HttpMethod.PUT, "/studentdetails").hasRole("ADMIN")  // Only ADMIN can PUT
                .requestMatchers(HttpMethod.DELETE, "/studentdetails").hasRole("ADMIN") // Only ADMIN can DELETE
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
}