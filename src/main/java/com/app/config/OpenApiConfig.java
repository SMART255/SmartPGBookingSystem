package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Smart PG Booking System API")

                        .version("1.0")

                        .description("REST APIs for Smart PG Booking System")

                        .contact(new Contact()

                                .name("Nikita Barhate")

                                .email("nikitabarhate0503@gmail.com")));
    }
}