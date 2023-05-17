package com.apikbuloso.productsanddepartment.services;

import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import com.apikbuloso.productsanddepartment.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    public List<DepartmentModel> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentModel> findById(Long id) {
        return departmentRepository.findById(id);
    }

    public Optional<DepartmentModel> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Transactional
    public Object save(DepartmentModel departmentModel) {
        return departmentRepository.save(departmentModel);
    }

    @Transactional
    public void delete(DepartmentModel departmentModel) {
        departmentRepository.delete(departmentModel);
    }
}