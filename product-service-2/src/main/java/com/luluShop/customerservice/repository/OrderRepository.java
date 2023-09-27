package com.luluShop.customerservice.repository;

import com.luluShop.customerservice.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

}
