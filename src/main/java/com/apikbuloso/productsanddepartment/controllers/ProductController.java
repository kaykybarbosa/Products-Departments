package com.apikbuloso.productsanddepartment.controllers;

import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> getAllProducts(){
       return productRepository.findAll();
    }

    @PostMapping
    public Object saveProduct(@RequestBody ProductModel productModel){
        return productRepository.save(productModel);
    }
}
