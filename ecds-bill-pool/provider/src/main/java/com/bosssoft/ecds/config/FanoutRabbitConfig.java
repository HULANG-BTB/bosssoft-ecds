package com.bosssoft.ecds.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
    public Queue errorQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "deadLetterExchange");
        map.put("x-dead-letter-routing-key", "deadLetterQueue");
        return new Queue("errorQueue", true, false, false, map);
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
    FanoutExchange fanoutErrorExchange() {
        return new FanoutExchange("fanoutErrorExchange");
    }

    @Bean
    Binding bindingBillWarnFirst() {
        return BindingBuilder.bind(billWarnFirst()).to(fanoutWarnExchange());
    }

    @Bean
    Binding bindingBillExhaustFirst() {
        return BindingBuilder.bind(billExhaustFirst()).to(fanoutExhaustExchange());
    }

    @Bean
    Binding bindingErrorQueue() {
        return BindingBuilder.bind(errorQueue()).to(fanoutErrorExchange());
    }
}
