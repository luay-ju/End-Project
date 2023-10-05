package com.luluShop.customerservice.service;

import com.luluShop.customerservice.entity.Product;
import com.luluShop.customerservice.repository.CartRepository;
import com.luluShop.customerservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceInterface{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer productId) {
        Optional<Product> optional = productRepository.findById(productId);
        Product product = null;
        if (optional.isPresent()){
            product = optional.get();
        }else {
            throw new RuntimeException(" Product not found for id :: " + productId);
        }
        return product;
    }

    @Override
    public void deleteProductBYId(Integer productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts(String keyword) {

        if (keyword != null){
            return productRepository.search(keyword);
        }
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findPageinated(int pageNo, int pageSize,
                                        String sortField, String sortDir) {

      Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
              Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);

    }

}
