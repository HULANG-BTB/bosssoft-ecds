package com.bosssoft.ecds.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue billWarnFirst() {
        return new Queue("warnQueueFirst");
    }

    @Bean
    public Queue billWarnSecond() {
        return new Queue("warnQueueSecond");
    }

    @Bean
    public Queue billExhaustFirst() {
        return new Queue("ExhaustQueueFirst");
    }

    @Bean
    public Queue billExhaustSecond() {
        return new Queue("ExhaustQueueSecond");
    }

    @Bean
    FanoutExchange fanoutWarnExchange() {
        return new FanoutExchange("fanoutWarnExchange");
    }

    @Bean
    FanoutExchange fanoutExhaustExchange() {
        return new FanoutExchange("fanoutExhaustExchange");
    }

    @Bean
    Binding bindingBillWarnFirst() {
        return BindingBuilder.bind(billWarnFirst()).to(fanoutWarnExchange());
    }

    @Bean
    Binding bindingBillExhaustFirst() {
        return BindingBuilder.bind(billExhaustFirst()).to(fanoutExhaustExchange());
    }
}
