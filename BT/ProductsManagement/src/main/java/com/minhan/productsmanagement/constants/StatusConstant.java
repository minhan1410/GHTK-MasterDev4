package com.minhan.productsmanagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusConstant {
    INACTIVE(0), ACTIVE(1);
    private int value;
}
