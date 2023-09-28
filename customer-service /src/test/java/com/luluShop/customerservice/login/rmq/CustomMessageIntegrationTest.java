package com.luluShop.customerservice.login.rmq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomMessageIntegrationTest {

    @Test
    public void testCustomMessageNotNull() {
        CustomMessage message = new CustomMessage();
        assertNotNull(message);
    }
}
