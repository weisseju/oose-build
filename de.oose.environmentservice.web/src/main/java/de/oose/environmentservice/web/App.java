package de.oose.environmentservice.web;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder((ObjectMapper)jackson2ObjectMapperBuilder().build());
    }

    @Primary
    @Bean
    public RestTemplate restTemplate() {

        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(jackson2ObjectMapperBuilder().build());

        RestTemplate template =
                new RestTemplate(Collections.singletonList(converter));

        return template;

    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.annotationIntrospector(new JaxbAnnotationIntrospector(
                TypeFactory.defaultInstance()));
        return builder;
    }
}
