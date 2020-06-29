package com.pavelnazaro.spring.services;

import com.pavelnazaro.spring.models.Product;
import com.pavelnazaro.spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product saveOrUpdateProduct(Product newProduct) {
        return productRepository.saveOrUpdateProduct(newProduct);
    }
}
