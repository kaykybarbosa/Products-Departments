package com.apikbuloso.productsanddepartment.controllers;

import com.apikbuloso.productsanddepartment.dtos.ProductDto;
import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.repository.DepartmentRepository;
import com.apikbuloso.productsanddepartment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api-rest")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/products")
    public List<ProductModel> getAllProducts(){
       return productRepository.findAll();
    }
    @GetMapping("/departments")
    public List<DepartmentModel> getAllDepartment(){ return departmentRepository.findAll(); }

    @PostMapping("/create-product")
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDto productDto){
        var productModel = new ProductModel();
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(productDto.getDepartment().getId());
        if (departmentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Department with this ID not found.");
        }
        productModel.setName(productDto.getName());
        productModel.setPrice(productDto.getPrice());
        productModel.setDepartment(departmentModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @PostMapping("/create-depart")
    public ResponseEntity<Object> saveDepartment(@RequestBody DepartmentModel departmentModel){
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findByName(departmentModel.getName());
        if (departmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(" Department already registered.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentRepository.save(departmentModel));
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable(value = "id") Long id){
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if(productModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product not found.");
        }

        productRepository.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Product deleted successfullly.");
    }
    @DeleteMapping("/delete-depart/{id}")
    public ResponseEntity<Object> deleteDepartById(@PathVariable(value = "id") Long id){
        Optional<DepartmentModel> departmentModelOptional = departmentRepository.findById(id);
        if (departmentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Department not found.");
        }

        departmentRepository.delete(departmentModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body(" Department deleted successfully.");
    }


}
