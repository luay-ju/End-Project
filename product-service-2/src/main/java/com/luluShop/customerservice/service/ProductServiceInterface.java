package com.luluShop.customerservice.service;

import com.luluShop.customerservice.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServiceInterface {

    Product saveProduct(Product product);
    Product getProductById(Integer productId);

    void deleteProductBYId(Integer productId);
    List<Product> getAllProducts(String keyword);
    Page<Product> findPageinated(int pageNo, int pageSize, String sortField, String sortDir);
}
