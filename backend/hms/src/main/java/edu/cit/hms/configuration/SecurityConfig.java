package edu.cit.hms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> 
                    authorizeRequests.anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("http://localhost:8080/{testing}"))
                .logout(logout -> logout.logoutSuccessUrl("http://localhost:8080/{logout}"))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
