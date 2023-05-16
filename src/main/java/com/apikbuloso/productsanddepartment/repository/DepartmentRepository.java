package com.apikbuloso.productsanddepartment.repository;

import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {

    public Optional<DepartmentModel> findByName(String name);

}
