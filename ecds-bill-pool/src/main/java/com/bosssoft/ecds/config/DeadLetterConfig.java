package com.bosssoft.ecds.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
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
