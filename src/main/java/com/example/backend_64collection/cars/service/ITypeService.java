package com.example.backend_64collection.cars.service;

import com.example.backend_64collection.cars.dto.TypeDto;

import java.util.List;

public interface ITypeService {

    boolean addType(TypeDto typeDto);

    List<TypeDto> getBrandTypeList(String brandId, String keyword);

    boolean deleteType(String typeId);

}
