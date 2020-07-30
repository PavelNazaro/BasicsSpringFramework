package com.pavelnazaro.spring.services;

import com.pavelnazaro.spring.exceptions.ProductNotFoundException;
import com.pavelnazaro.spring.model.Product;
import com.pavelnazaro.spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public static final int COUNT_ROWS_ON_PAGES = 5;

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product saveOrUpdate(Product product){
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't find product with id=" + id));
    }

    public Product findByName(String title) {
        return productRepository.findOneByTitle(title);
    }

    public List<Product> findByMinCost(int minCost){
        return productRepository.findAllByCostGreaterThan(minCost);
    }

    public List<Product> findByMaxCost(int maxCost){
        return productRepository.findAllByCostLessThan(maxCost);
    }

    public List<Product> findByBetweenCost(int minCost, int maxCost){
        return productRepository.findAllByCostBetween(minCost, maxCost);
    }

    public Page<Product> findByPage(int pageNumber, int pageSize){
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> specification, Integer page){
        if (page < 1L){
            page = 1;
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, COUNT_ROWS_ON_PAGES));
    }

    public int getCountRowsOnPage() {
        return COUNT_ROWS_ON_PAGES;
    }
}
