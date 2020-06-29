package com.pavelnazaro.spring.controllers;

import com.pavelnazaro.spring.models.Product;
import com.pavelnazaro.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "allProducts";
    }

    @GetMapping("/add")
    public String showAndForm(){
        return "addProductsForm";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product newProduct){
        productService.saveOrUpdateProduct(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "editProductsForm";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct){
        productService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products/";
    }
}
