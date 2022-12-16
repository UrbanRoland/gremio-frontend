package com.gremio.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /* In Spring 3.0
    authorizeRequests() -> authorizeHttpRequests()
    antMatcher() -> requestMatchers()
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       return http

               .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/user").permitAll();
                    auth.requestMatchers("/admin").permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
