package com.luluShop.customerservice.repository;

import com.luluShop.customerservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT product FROM Product product WHERE CONCAT(product.id, ' ', product.productName, ' ', product.price)  LIKE %?1%")
    public List<Product> search(String keyword);
    public Product findByproductName(String productNAme);



}
