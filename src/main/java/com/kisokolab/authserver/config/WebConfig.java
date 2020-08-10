package com.kisokolab.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean("userPasswordEncoder")
    public BCryptPasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean("clientPasswordEncoder")
    public BCryptPasswordEncoder clientPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}