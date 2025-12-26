package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 1. Required by your specific hosting environment to make "Try it out" work
                .servers(List.of(
                        new Server().url("https://9005.vs.amypo.ai")
                ))
                // 2. Required by the Project Document (Source 2, Section 9)
                .info(new Info()
                        .title("Skill Based Volunteer Task Assignor API")
                        .version("1.0")
                        .description("Backend API for Managing Volunteers and Tasks"));
    }
}