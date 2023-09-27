package com.luluShop.customerservice.repository;


import com.luluShop.customerservice.cart.Cart;
import com.luluShop.customerservice.entity.Product;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    /*
    @Query("SELECT cart FROM Cart cart WHERE CONCAT(cart.id, ' ', cart.productName, ' ', cart.price, cart.referenceOfUserId)  LIKE %?1%")
    public List<Cart> search(String keyword);
    public Cart findCartById(String id);
*/

}
