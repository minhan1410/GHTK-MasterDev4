package com.minhan.productsmanagement.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    Integer page;

    @JsonProperty("page_size")
    Integer pagesize;

    Long total;
}
