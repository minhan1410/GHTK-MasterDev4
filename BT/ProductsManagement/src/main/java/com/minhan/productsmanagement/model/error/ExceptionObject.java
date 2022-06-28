package com.minhan.productsmanagement.model.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionObject extends RuntimeException{
    String message;
}
