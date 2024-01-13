package com.example.usingthirdpartyapidemo.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    //this says mujhe restTemplate ka bean banana h
    //@configuration tells spring yeh special class h ,please go through it , yeh class ek bean banane bol rha h
    public RestTemplate createRestTemplate()
    {
        return new RestTemplateBuilder().build();
    }
}
