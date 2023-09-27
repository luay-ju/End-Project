package com.luluShop.customerservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    private String productName;
    private String marke;
    private String size;
    private double price;
    @Column(nullable = true, length = 64)
    private String photo;
    private String ref;

    @Transient
    public String getPhotosImagePath(){
        if(photo == null) return null;
        return "/End-Project/product-photos/" + id + "/" + photo;
    }


}
