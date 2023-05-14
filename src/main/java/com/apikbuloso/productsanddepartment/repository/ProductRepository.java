package com.apikbuloso.productsanddepartment.repository;

import com.apikbuloso.productsanddepartment.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
