package com.luluShop.customerservice.orders;

import com.luluShop.customerservice.cart.Cart;
import com.luluShop.customerservice.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String invoice;
    private int userId;


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", invoice='" + invoice + '\'' +
                ", userId=" + userId +
                '}';
    }
}
