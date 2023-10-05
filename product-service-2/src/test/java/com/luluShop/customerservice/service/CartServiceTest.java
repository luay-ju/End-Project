package com.luluShop.customerservice.service;
import com.luluShop.customerservice.cart.Cart;
import com.luluShop.customerservice.repository.CartRepository;
import com.luluShop.customerservice.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService();
        cartService.setCartRepository(cartRepository);
    }

    @Test
    public void testSaveItemsInCart() {
        Cart cart = new Cart();
        cartService.saveItemsInCart(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    public void testGetAllCartItems() {
        List<Cart> cartList = new ArrayList<>();
        when(cartRepository.findAll()).thenReturn(cartList);

        List<Cart> result = cartService.getAllCartItems();

        assertEquals(cartList, result);
    }

    @Test
    public void testGetItemsById() {
        Integer itemId = 1;
        Cart cart = new Cart();
        when(cartRepository.getById(itemId)).thenReturn(cart);

        Cart result = cartService.getItemsById(itemId);

        assertEquals(cart, result);
    }

    @Test
    public void testDeleteProductById() {
        Integer productId = 1;
        cartService.deleteProductBYId(productId);
        verify(cartRepository, times(1)).deleteById(productId);
    }
}
