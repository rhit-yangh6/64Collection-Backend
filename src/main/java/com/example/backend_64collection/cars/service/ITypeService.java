package com.example.backend_64collection.cars.service;

import com.example.backend_64collection.cars.dto.BrandTypeDto;
import com.example.backend_64collection.cars.dto.TypeDto;

import java.util.List;

public interface ITypeService {

    boolean addType(TypeDto typeDto);

    List<TypeDto> getBrandTypeList(String brandId, String keyword);

    List<BrandTypeDto> getCategoryTypeList(String category);

    List<TypeDto> getAllTypeList();

    List<BrandTypeDto> getFeaturedTypeList(int count);

    TypeDto getRandomType();

    boolean deleteType(String typeId);

    void increaseViewTimes(String typeId);

    TypeDto getSingleType(String typeId);

}
