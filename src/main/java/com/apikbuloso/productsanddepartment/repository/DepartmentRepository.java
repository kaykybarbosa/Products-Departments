package com.apikbuloso.productsanddepartment.repository;

import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {

    public Optional<DepartmentModel> findByName(String name);

}
