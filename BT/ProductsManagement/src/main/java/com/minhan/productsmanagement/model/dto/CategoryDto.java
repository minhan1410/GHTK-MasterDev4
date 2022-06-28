package com.minhan.productsmanagement.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Ten lon hon 0")
    @Length(max = 100, message = "Ten <= 100 ki tu>")
    private String name;

    private String description;
}
