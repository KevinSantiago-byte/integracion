package com.safco.peru.integracion.friomamut.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean("friomamutWebClient")
    public WebClient friomamutWebClient(
            @Value("${friomamut.api.base-url}") String baseUrl
    ) {

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024)) // 16 MB
                .build();
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .exchangeStrategies(strategies)
                .build();
    }

}
