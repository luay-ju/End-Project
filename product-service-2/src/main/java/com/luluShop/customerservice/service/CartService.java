package com.luluShop.customerservice.service;

import com.luluShop.customerservice.cart.Cart;
import com.luluShop.customerservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public void saveItemsInCart(Cart cart){
        cartRepository.save(cart);
    }

    public List<Cart> getAllCartItems(){
        return cartRepository.findAll();
    }

    public  Cart getItemsById(Integer number){
        return cartRepository.getById(number);
    }


    public void deleteProductBYId(Integer productId) {
        this.cartRepository.deleteById(productId);
    }



}
