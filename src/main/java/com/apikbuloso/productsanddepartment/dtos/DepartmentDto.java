package com.apikbuloso.productsanddepartment.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDto {
    @NotBlank
    private String name;
}
