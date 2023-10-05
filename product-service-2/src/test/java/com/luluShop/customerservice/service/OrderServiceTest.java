package com.luluShop.customerservice.service;

import com.luluShop.customerservice.orders.Order;
import com.luluShop.customerservice.repository.OrderRepository;
import com.luluShop.customerservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService();
        orderService.setOrderRepository(orderRepository);
    }

    @Test
    public void testSaveItems() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        orderService.saveItems(order);

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetAllCartItems() {
        List<Order> orderList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> result = orderService.getAllCartItems();

        assertEquals(orderList, result);
    }


}
