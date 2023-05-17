package com.apikbuloso.productsanddepartment.controllers;

import com.apikbuloso.productsanddepartment.dtos.ProductDto;
import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.repository.DepartmentRepository;
import com.apikbuloso.productsanddepartment.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping
    public List<ProductModel> getAllProducts(){
       return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDto productDto){
        var productModel = new ProductModel();
        productModel.setName(productDto.getName());
        productModel.setPrice(productDto.getPrice());

        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(productDto.getDepartment().getId());
        if (departmentModelOptional.isEmpty()){
            var departmentModel = departmentRepository.save(productDto.getDepartment());
            productModel.setDepartment(departmentModel);
        } else {

            productModel.setDepartment(departmentModelOptional.get());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @PostMapping("/create-depart")
        public ResponseEntity<Object> saveDepartment(@RequestBody DepartmentModel departmentModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentRepository.save(departmentModel));
    }


}
