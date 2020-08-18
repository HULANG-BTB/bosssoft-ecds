package com.bosssoft.ecds.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterConfig {

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("deadLetterQueue");
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange("deadLetterExchange");
    }

    @Bean
    Binding bindingDeadLetter() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
    }
}
