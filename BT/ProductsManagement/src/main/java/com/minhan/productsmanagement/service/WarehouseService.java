package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.model.dto.WarehoureDto;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;

public interface WarehouseService {
    ResponsePage get(int page, int pageSize);

    ResponseObject create(WarehoureDto warehoureDto);

    ResponseObject update(WarehoureDto warehoureDto);

    ResponseObject delete(Long warehouseId);
}
