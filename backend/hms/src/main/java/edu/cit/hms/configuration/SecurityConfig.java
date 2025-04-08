package edu.cit.hms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Restrict access to admission endpoints
                        .requestMatchers("/api/admission/**").hasAnyRole("ADMIN", "DOCTOR")
                        
                        // Restrict access to other endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/staff/**").hasRole("STAFF")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        
                        // Allow all other requests (e.g., public endpoints)
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.defaultSuccessUrl("/dashboard", true)) // Redirect after login
                .logout(logout -> logout.logoutSuccessUrl("/")) // Redirect after logout
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity (not recommended for production)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
