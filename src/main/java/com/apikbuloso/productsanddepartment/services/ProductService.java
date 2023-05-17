package com.apikbuloso.productsanddepartment.services;

import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Object save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public Optional<ProductModel> findById(Long id) {
        return productRepository.findById(id);
    }
    @Transactional
    public void delete(ProductModel productModel) {
         productRepository.delete(productModel);
    }
}
