package com.soa.orders.infrastructure.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConverterConfig {
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
