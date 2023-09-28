package com.luluShop.customerservice.login.rmq;

import com.luluShop.customerservice.login.rmq.CustomMessage;
import com.luluShop.customerservice.login.rmq.MQConfig;
import com.luluShop.customerservice.login.rmq.MessagePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Date;

import static org.mockito.Mockito.*;

@SpringBootTest
public class RabbitMQSendMessageTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue messageQueue;


    @Autowired
    private MessagePublisher messagePublisher;

    @BeforeEach
    public void setUp() {
        when(messagePublisher.getUserInfoEamil()).thenReturn("test@example.com");
        when(messagePublisher.getUserInfoId()).thenReturn(123);
    }


    @Test
    public void testSendMessage() {
        CustomMessage message = new CustomMessage();
        message.setMessageId("testId");
        message.setMessage("testMessage");

        rabbitTemplate.convertAndSend(messageQueue.getName(), message);
    }

}
