package com.pavelnazaro.spring.controllers;

import com.pavelnazaro.spring.model.Product;
import com.pavelnazaro.spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber){
        Specification<Product> specification = Specification.where(null);
        int countProducts = productService.findAll().size();
        List<Product> products = productService.findAll(specification, pageNumber).getContent();
        model.addAttribute("products", products);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("maxPage", countProducts/productService.getCountRowsOnPage());
        return "allProducts";
    }

    @GetMapping("/add")
    public String showAddForm(){
        return "addProductsForm";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product newProduct){
        productService.saveOrUpdate(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "editProductsForm";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifyProduct){
        productService.saveOrUpdate(modifyProduct);
        return "redirect:/products/";
    }

    @GetMapping("/info_by_name")
    @ResponseBody
    public Product infoByName(@RequestParam String name){
        return productService.findByName(name);
    }

    @GetMapping("/find_by_min_cost")
    @ResponseBody
    public List<Product> findProductByMinCost(@RequestParam int cost){
        return productService.findByMinCost(cost);
    }

    @GetMapping("/find_by_max_cost")
    @ResponseBody
    public List<Product> findProductByMaxCost(@RequestParam int cost){
        return productService.findByMaxCost(cost);
    }

    @GetMapping("/find_by_between_cost")
    @ResponseBody
    public List<Product> findProductByBetweenCost(@RequestParam int minCost, @RequestParam int maxCost){
        return productService.findByBetweenCost(minCost, maxCost);
    }
}
