package com.apikbuloso.productsanddepartment.dtos;

import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private Long id;
    private String name;
    private double price;
    private DepartmentModel departmentDto;
}
