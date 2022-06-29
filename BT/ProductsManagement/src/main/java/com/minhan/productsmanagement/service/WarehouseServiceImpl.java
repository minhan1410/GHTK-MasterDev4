package com.minhan.productsmanagement.service;

import com.minhan.productsmanagement.constants.StatusConstant;
import com.minhan.productsmanagement.model.dto.WarehoureDto;
import com.minhan.productsmanagement.model.entity.DistrictEntity;
import com.minhan.productsmanagement.model.entity.ProvinceEntity;
import com.minhan.productsmanagement.model.entity.WarehoureEntity;
import com.minhan.productsmanagement.model.error.ExceptionObject;
import com.minhan.productsmanagement.model.response.Pagination;
import com.minhan.productsmanagement.model.response.ResponseObject;
import com.minhan.productsmanagement.model.response.ResponsePage;
import com.minhan.productsmanagement.repository.DistrictRepository;
import com.minhan.productsmanagement.repository.ProvinceRepository;
import com.minhan.productsmanagement.repository.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponsePage get(int page, int pageSize) {
        Page<WarehoureDto> warehoureDto = warehouseRepository.findByStatusEquals(1, PageRequest.of(page, pageSize))
                .map(warehoureEntity -> modelMapper.map(warehoureEntity, WarehoureDto.class));

        return ResponsePage.builder().success(true).message("Thanh cong").data(warehoureDto.getContent())
                .pagination(Pagination.builder().page(page).pagesize(pageSize)
                        .total(warehoureDto.getTotalElements()).build()).build();
    }

    @Override
    public ResponseObject create(WarehoureDto warehoureDto) {
        Optional<ProvinceEntity> optionalProvinceEntity = provinceRepository.findById(warehoureDto.getProvinceId());
        if (!optionalProvinceEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID Province khong ton tai").build();
        }

        Optional<DistrictEntity> optionalDistrictEntity = districtRepository.findById(warehoureDto.getDistinctId());
        if (!optionalDistrictEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID District khong ton tai").build();
        }

        WarehoureEntity warehoureEntity = modelMapper.map(warehoureDto, WarehoureEntity.class);
        warehoureEntity.setStatus(StatusConstant.ACTIVE.getValue());
        warehoureEntity.setAddress(warehoureEntity.getAddress() + ", " + optionalDistrictEntity.get().getName() + ", " + optionalProvinceEntity.get().getName());

        return ResponseObject.builder().success(true).message("Thanh cong")
                .data(modelMapper.map(warehouseRepository.save(warehoureEntity), WarehoureDto.class)).build();
    }

    @Override
    public ResponseObject update(WarehoureDto warehoureDto) {
        WarehoureEntity warehoureEntityRepository = checkIdWarehoseIsPresent(warehoureDto.getId());
        if (warehoureEntityRepository.getStatus() == StatusConstant.INACTIVE.getValue()) {
            throw ExceptionObject.builder().message("ID Warehoure da xoa truoc do").build();
        }

        Optional<ProvinceEntity> optionalProvinceEntity = provinceRepository.findById(warehoureDto.getProvinceId());
        if (!optionalProvinceEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID Province khong ton tai").build();
        }

        Optional<DistrictEntity> optionalDistrictEntity = districtRepository.findById(warehoureDto.getDistinctId());
        if (!optionalDistrictEntity.isPresent()) {
            throw ExceptionObject.builder().message("ID District khong ton tai").build();
        }

        WarehoureEntity warehoureEntity = modelMapper.map(warehoureDto, WarehoureEntity.class);
        warehoureEntity.setStatus(warehoureEntityRepository.getStatus());
        warehoureEntity.setAddress(warehoureEntity.getAddress() + ", " + optionalDistrictEntity.get().getName() + ", " + optionalProvinceEntity.get().getName());

        return ResponseObject.builder().success(true).message("Thanh cong")
                .data(modelMapper.map(warehouseRepository.save(warehoureEntity), WarehoureDto.class)).build();
    }

    @Override
    public ResponseObject delete(Long warehouseId) {
        WarehoureEntity warehoureEntityRepository = checkIdWarehoseIsPresent(warehouseId);
        if (warehoureEntityRepository.getStatus() == StatusConstant.INACTIVE.getValue()) {
            throw ExceptionObject.builder().message("ID Warehoure da xoa truoc do").build();
        }

        warehoureEntityRepository.setStatus(StatusConstant.INACTIVE.getValue());
        warehouseRepository.save(warehoureEntityRepository);

        return ResponseObject.builder().success(true).message("Thanh cong").data(null).build();
    }

    public WarehoureEntity checkIdWarehoseIsPresent(Long id){
        Optional<WarehoureEntity> optional = warehouseRepository.findById(id);
        if (!optional.isPresent()) {
            throw ExceptionObject.builder().message("ID Warehoure khong ton tai").build();
        }

        return optional.orElseGet(null);
    }
}
