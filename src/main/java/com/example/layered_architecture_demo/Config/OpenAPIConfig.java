package com.example.layered_architecture_demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .description("Spring Boot API for Employee Management")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Demo User")
                                .email("demo@example.com")));
    }
}
