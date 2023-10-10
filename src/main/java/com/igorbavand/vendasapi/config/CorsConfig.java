package com.igorbavand.vendasapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String PERMIT_ALL = "*";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(PERMIT_ALL)
                .allowedOrigins(PERMIT_ALL)
                .allowedHeaders(PERMIT_ALL)
                .allowCredentials(false)
                .maxAge(-1);
    }
}
