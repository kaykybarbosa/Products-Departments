package com.apikbuloso.productsanddepartment.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="TB_DEPARTMENT")
public class DepartmentModel  {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
