package com.staj.liquibasepractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //hepsini gormek icin etki etmeyecek sekilde yazdim
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .headers(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .sessionManagement(Customizer.withDefaults())
                .exceptionHandling(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated())
                .build();
    }
}
