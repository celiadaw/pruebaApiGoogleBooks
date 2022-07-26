package com.example.pruebaapigooglebooks.configurations;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
    /*
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
      // Do any additional configuration here
      return builder.build();
  }*/
    @Bean
    public RestTemplate restTemplate(){


        return new RestTemplate();

    }
}
