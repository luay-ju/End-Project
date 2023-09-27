package com.luluShop.customerservice.login.rmq;

import com.luluShop.customerservice.login.controller.UserController;
        import com.luluShop.customerservice.login.entity.User;
        import org.springframework.amqp.rabbit.core.RabbitTemplate;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

        import com.luluShop.customerservice.login.controller.*;

        import java.util.Date;
        import java.util.UUID;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    public Integer getUserInfoId(){
        return UserController.getTheUserId();
    }

    public String getUserInfoEamil(){
        return UserController.getTheUserEmail();
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message) {
        message.setMessageId(getUserInfoEamil());
        // message.setMessageId(UUID.randomUUID().toString());
        message.setMessage(String.valueOf(getUserInfoId()));
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, message);

        System.out.println(" User Id: "+getUserInfoId());

        return "Message Published";
    }
}
