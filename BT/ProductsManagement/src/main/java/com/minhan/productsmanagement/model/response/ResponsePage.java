package com.minhan.productsmanagement.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePage {
    private Boolean success;
    private String message;
    private Object data;
    private Pagination pagination;
}
