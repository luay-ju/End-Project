package com.luluShop.customerservice.rmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    public static String messageEmail;
    public static String messageUserId;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage message) {
        System.out.println(message);
        messageEmail = message.getMessageId();
        messageUserId = message.getMessage();
    }

}
