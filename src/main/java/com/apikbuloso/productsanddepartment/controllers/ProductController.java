package com.apikbuloso.productsanddepartment.controllers;

import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> saveProduct(@RequestBody ProductModel productModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }
}
