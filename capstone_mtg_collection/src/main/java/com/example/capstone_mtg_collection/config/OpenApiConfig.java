package com.example.capstone_mtg_collection.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI mtgVaultOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MTG Vault API")
                        .version("v1")
                        .description("Endpoints for cards, decks, and deck contents"));
    }
}
