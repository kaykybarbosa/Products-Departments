package com.apikbuloso.productsanddepartment.repository;

import com.apikbuloso.productsanddepartment.models.ProductModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
