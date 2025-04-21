package edu.cit.hms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
