package br.com.kenzie.swagger_docs.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class AppConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    OpenAPI openAPIConfig() {
        var securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT");
        var components = new Components().addSecuritySchemes("bearer-key", securityScheme);

        return new OpenAPI().components(components);
    }
}
