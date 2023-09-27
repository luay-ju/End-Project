package com.luluShop.customerservice.login;

import com.luluShop.customerservice.login.rmq.CustomMessage;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMQSendMessageTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue messageQueue;

    @Test
    public void testSendMessage() {
        CustomMessage message = new CustomMessage();
        message.setMessageId("testId");
        message.setMessage("testMessage");

        rabbitTemplate.convertAndSend(messageQueue.getName(), message);
    }
}
