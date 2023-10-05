package com.luluShop.customerservice.controller;

import com.luluShop.customerservice.cart.Cart;

import com.luluShop.customerservice.entity.Product;

import com.luluShop.customerservice.service.CartService;

import com.luluShop.customerservice.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private CartService cartService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.reset(cartService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    }

    @Test
    public void testShowNewProductForm() {
        Model model = mock(Model.class);

        String result = productController.Project2(model);

        assertEquals("Add_product", result);
    }

    @Test
    public void testSaveProduct() throws IOException {
        Product product = new Product();
        product.setId(1);
        MultipartFile multipartFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", new byte[0]);

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        RedirectView result = productController.saveProduct(product, multipartFile);

        assertEquals("/", result.getUrl());
        verify(productService, times(1)).saveProduct(any(Product.class));
    }


    @Test
    public void testAddToCart() {
        // Mock-Produkt und Warenkorb
        int productId = 1;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        mockProduct.setProductName("Test Product");
        mockProduct.setPrice(10.0);
        String mockUserId = "mockUserId";
        Cart mockCart = new Cart(productId, "Test Product", 10.0, mockUserId);

        // Mock, wie productService.getProductById reagieren soll
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        // Mocken der Methode cartService.saveItemsInCart, die void ist
        doNothing().when(cartService).saveItemsInCart(any(Cart.class));

        // Aufruf der Methode addToCart
        String result = productController.addToCart(productId);

        // Überprüfen, ob die Methode "addToCart" die erwartete Umleitung zurückgibt
        assertEquals("redirect:/cart", result);

        // Überprüfen, ob productService.getProductById aufgerufen wurde
        verify(productService, times(1)).getProductById(productId);

        // Überprüfen, ob cartService.saveItemsInCart aufgerufen wurde
        verify(cartService, times(1)).saveItemsInCart(any(Cart.class));
    }

    @Test
    public void testDeleteProductFromCart() {
        int cartItemId = 1;

        String result = productController.deleteProductFromCart(cartItemId);

        assertEquals("redirect:/cart", result);
        verify(cartService, times(1)).deleteProductBYId(cartItemId);
    }


    @Test
    public void testGoToPaymentPage() {
        String result = productController.goToPaymentPage(mock(Model.class));

        assertEquals("/payment", result);
    }


}
