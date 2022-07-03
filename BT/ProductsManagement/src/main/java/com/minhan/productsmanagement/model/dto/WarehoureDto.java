package com.minhan.productsmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class WarehoureDto {
    private Long id;

    @NotBlank(message = "Ten lon hon 0")
    @Length(max = 100, message = "Ten <= 100 ki tu>")
    private String name;

    private String address;

    @JsonProperty("province_id")
    private Long provinceId;

    @JsonProperty("distinct_id")
    private Long distinctId;
}
