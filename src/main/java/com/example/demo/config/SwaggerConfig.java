package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 1. Define the Security Scheme (The "Authorize" button config)
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                // 2. Apply the Security Scheme globally
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                // 3. Keep your Server URL (Critical for your cloud environment)
                .servers(List.of(
                        new Server().url("https://9286.408procr.amypo.ai/") // Ensure this matches your current cloud URL
                ))
                // 4. Keep the Info block
                .info(new Info()
                        .title("Skill Based Volunteer Task Assignor API")
                        .version("1.0")
                        .description("Backend API for Managing Volunteers and Tasks"));
    }
}`