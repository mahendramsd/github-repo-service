package com.msd.restservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 10:04 am
 * @project IntelliJ IDEA
 */
@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GitHub Repository Details API")
                        .version("1.0")
                        .description("API to fetch and cache GitHub repository details"));
    }
}
