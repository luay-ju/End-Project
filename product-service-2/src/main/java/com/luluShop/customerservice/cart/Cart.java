package com.luluShop.customerservice.cart;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
public  class Cart {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;
    private double price;
    private String referenceOfUserId;

    @Override
    public String toString() {
        return " \n " +
                " [ Order ID: " + id +
                " - Product Name: '" + productName + '\'' +
                " - Price: " + price +
                " - User ID: '" + referenceOfUserId + " ] \n"+'\'' +
                '\n';
    }
}
