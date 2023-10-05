package com.luluShop.customerservice.rmq;
import com.luluShop.customerservice.rmq.CustomMessage;
import com.luluShop.customerservice.rmq.MessageListener;
import com.luluShop.customerservice.rmq.MQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@SpringBootTest
public class MessageListenerIntegrationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private MessageListener messageListener;


}
