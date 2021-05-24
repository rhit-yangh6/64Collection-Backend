package com.example.backend_64collection.cars.service;

import com.example.backend_64collection.cars.dto.BrandDto;

import java.util.List;

public interface IBrandService {
    boolean addBrand(BrandDto brandDto);

    List<BrandDto> getBrands(String keyword);

    boolean removeBrand(String brandId);

    BrandDto getSingleBrandInfo(String brandId);
}
