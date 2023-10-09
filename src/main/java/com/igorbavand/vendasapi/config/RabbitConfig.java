package com.igorbavand.vendasapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app-config.rabbit.queues.enviar-email.exchange}")
    private String enviarEmailExchange;

    @Value("${app-config.rabbit.queues.enviar-email.name}")
    private String enviarEmailQueue;

    @Value("${app-config.rabbit.queues.enviar-email.key}")
    private String enviarEmailKey;

    @Bean
    public DirectExchange directExchangeEnviarEmail() {
        return new DirectExchange(enviarEmailExchange);
    }

    @Bean
    public Queue enviarEmailQueue() {
        return new Queue(enviarEmailQueue);
    }

    @Bean
    public Binding bindingEnviarEmail() {
        return BindingBuilder.bind(enviarEmailQueue()).to(directExchangeEnviarEmail()).with(enviarEmailKey);
    }
}


