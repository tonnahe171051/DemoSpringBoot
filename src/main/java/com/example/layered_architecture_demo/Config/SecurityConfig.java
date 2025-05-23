package com.example.layered_architecture_demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.layered_architecture_demo.Security.JwtAuthenticationFilter;
import com.example.layered_architecture_demo.Service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;
    private final UserServiceImpl uds;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter,
            UserServiceImpl uds) {
        this.jwtFilter = jwtFilter;
        this.uds = uds;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
            PasswordEncoder encoder) throws Exception {
        DaoAuthenticationProvider prov = new DaoAuthenticationProvider();
        prov.setUserDetailsService(uds);
        prov.setPasswordEncoder(encoder);
        return new ProviderManager(prov);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(cs -> cs.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/api-docs/**", "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
