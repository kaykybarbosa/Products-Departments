package com.apikbuloso.productsanddepartment.dtos;

import com.apikbuloso.productsanddepartment.models.DepartmentModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    @NotBlank
    private String name;
    @NotNull
    private double price;
    @NotEmpty
    private DepartmentModel department;
}
