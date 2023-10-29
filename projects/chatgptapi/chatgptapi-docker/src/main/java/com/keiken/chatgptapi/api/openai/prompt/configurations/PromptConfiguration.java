package com.keiken.chatgptapi.api.openai.prompt.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PromptConfiguration {

    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;

    @Bean
    public RestTemplate template() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + OPENAI_API_KEY);
            return execution.execute(request, body);
        });
        return restTemplate;
    }

}
