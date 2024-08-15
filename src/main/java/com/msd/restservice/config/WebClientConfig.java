package com.msd.restservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 8:48 am
 * @project IntelliJ IDEA
 */
public class WebClientConfig {

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }
}
