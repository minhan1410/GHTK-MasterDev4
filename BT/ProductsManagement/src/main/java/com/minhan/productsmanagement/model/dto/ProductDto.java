package com.minhan.productsmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "Ten lon hon 0")
    @Length(max = 100, message = "Ten <= 100 ki tu>")
    private String name;

    @Min(value = 1, message = "Gia > 0")
    private Double price;

    private String sku;

    private String description;

    @JsonProperty("category_id")
    private Long categoryId;
}
