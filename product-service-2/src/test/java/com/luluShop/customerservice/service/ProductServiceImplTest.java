package com.luluShop.customerservice.service;

import com.luluShop.customerservice.entity.Product;
import com.luluShop.customerservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl();
        productService.setProductRepository(productRepository);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertEquals(product, savedProduct);
    }

    @Test
    public void testGetProductById() {
        Integer productId = 1;
        Product expectedProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        Product result = productService.getProductById(productId);

        assertEquals(expectedProduct, result);
    }

    @Test
    public void testGetProductByIdNotFound() {
        Integer productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProductById(productId));
    }

    @Test
    public void testDeleteProductById() {
        Integer productId = 1;
        doNothing().when(productRepository).deleteById(productId);

        productService.deleteProductBYId(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> result = productService.getAllProducts(null);

        assertEquals(expectedProducts, result);
    }

    @Test
    public void testFindPageinated() {
        int pageNo = 1;
        int pageSize = 10;
        String sortField = "productName";
        String sortDir = "asc";
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        Page<Product> expectedPage = new PageImpl<>(expectedProducts);
        Sort sort = Sort.by(Sort.Order.asc("productName"));
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        when(productRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Product> result = productService.findPageinated(pageNo, pageSize, sortField, sortDir);

        assertEquals(expectedPage, result);
    }
}
