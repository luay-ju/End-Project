package com.luluShop.customerservice.login.rmq;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MQConfigIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Queue queue;

    @Autowired
    private TopicExchange exchange;

    @Autowired
    private Binding binding;

    @Test
    public void testQueueBean() {
        assertNotNull(queue);
    }

    @Test
    public void testExchangeBean() {
        assertNotNull(exchange);
    }

    @Test
    public void testBindingBean() {
        assertNotNull(binding);
    }

    // Fügen Sie hier weitere Integrationstests für MQConfig hinzu
}
