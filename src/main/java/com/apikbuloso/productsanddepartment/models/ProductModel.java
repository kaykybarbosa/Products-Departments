package com.apikbuloso.productsanddepartment.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="TB_PRODUCTS")
public class ProductModel {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private double price;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private DepartmentModel department;
}