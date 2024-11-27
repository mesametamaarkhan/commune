package com.example.commune.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll() // Allow all to access home and static resources
                .anyRequest().permitAll() // Allow any request without authentication
                .and()
                .csrf().disable(); // Disable CSRF protection if needed (be careful with disabling it)

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/index.html", "/static/**", "/home").permitAll()  // Allow access to index.html, static resources, and home
//                .anyRequest().authenticated()  // Secure other pages
//                .and()
//                .formLogin()
//                .loginPage("/login")  // Optional login page
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//
//        return http.build();
//    }
}
