package com.pavelnazaro.spring.repositories;

import com.pavelnazaro.spring.models.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;
    private Long id = 2L;

    @PostConstruct

    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1L, "Product1", 100));
        this.products.add(new Product(2L, "Product2", 200));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id){
        for (Product p : products) {
            if (p.getId().equals(id)){
                return p;
            }
        }
        throw new RuntimeException("Product not found!");
    }

    public Product saveOrUpdateProduct(Product product) {
        if (product.getId() == null){
            product.setId(++id);
            products.add(product);
            return product;
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())){
                    products.set(i, product);
                    return product;
                }
            }
        }
        throw new RuntimeException("Unreal Exception!");
    }
}
