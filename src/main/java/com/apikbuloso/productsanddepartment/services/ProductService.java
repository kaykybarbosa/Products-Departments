package com.apikbuloso.productsanddepartment.services;

import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<ProductModel> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
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
