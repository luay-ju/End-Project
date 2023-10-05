package com.luluShop.customerservice.service;

import com.luluShop.customerservice.cart.Cart;
import com.luluShop.customerservice.orders.Order;
import com.luluShop.customerservice.repository.CartRepository;
import com.luluShop.customerservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveItems(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAllCartItems(){
        return orderRepository.findAll();
    }

    public Order getItemsById(Integer number){
        return orderRepository.getById(number);
    }

    public List<Order> getItemsByUserId(int userId){

        return null;
    }

}

