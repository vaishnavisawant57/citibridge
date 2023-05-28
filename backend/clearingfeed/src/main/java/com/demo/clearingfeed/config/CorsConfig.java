package com.demo.clearingfeed.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow CORS for all endpoints
                .allowedOriginPatterns("http://localhost:3000")  // Add your frontend URL pattern here
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Add the allowed HTTP methods
                .allowedHeaders("Content-Type", "Authorization");  // Add the allowed request headers
    }
}



