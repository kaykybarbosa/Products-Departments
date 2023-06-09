package com.apikbuloso.productsanddepartment.controllers;

import com.apikbuloso.productsanddepartment.dtos.DepartmentDto;
import com.apikbuloso.productsanddepartment.dtos.ProductDto;
import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import com.apikbuloso.productsanddepartment.models.ProductModel;
import com.apikbuloso.productsanddepartment.services.DepartmentService;
import com.apikbuloso.productsanddepartment.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api-rest")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductModel>> getAllProducts(Pageable pageable){
       return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }
    @GetMapping("/departments")
    public ResponseEntity<Page<DepartmentModel>> getAllDepartment(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findAll(pageable));
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (productModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product with this ID not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }
    @GetMapping("/departments/{id}")
    public ResponseEntity<Object> getOneDepartment(@PathVariable(value = "id") Long id){
        Optional<DepartmentModel> departmentModelOptional = departmentService.findById(id);
        if (departmentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Department with this ID not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(departmentModelOptional.get());
    }
    @GetMapping("/products-depart/{id}")
    public List<ProductModel> getProductByDepartId(@PathVariable(value = "id") Long id){
        return productService.findProductByDepartId(id);
    }
    @PostMapping("/create-product")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){
        var productModel = new ProductModel();
        Optional<DepartmentModel> departmentModelOptional = departmentService.findById(productDto.getDepartment().getId());
        if (departmentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Department with this ID not found.");
        }
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setDepartment(departmentModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }
    @PostMapping("/create-depart")
    public ResponseEntity<Object> saveDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        Optional<DepartmentModel> departmentModelOptional = departmentService.findByName(departmentDto.getName());
        if (departmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(" Department already registered.");
        }
        var departmentModel = new DepartmentModel();
        departmentModel.setName(departmentDto.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(departmentModel));
    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable( value = "id") Long id,
                                                @RequestBody @Valid ProductDto productDto){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (productModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product with this ID not found.");
        }

        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable(value = "id") Long id){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if(productModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Product not found.");
        }

        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Product deleted successfullly.");
    }
    @DeleteMapping("/delete-depart/{id}")
    public ResponseEntity<Object> deleteDepartById(@PathVariable(value = "id") Long id){
        Optional<DepartmentModel> departmentModelOptional = departmentService.findById(id);
        if (departmentModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Department not found.");
        }

        departmentService.delete(departmentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(" Department deleted successfully.");
    }
}
