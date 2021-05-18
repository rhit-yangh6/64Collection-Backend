package com.example.backend_64collection.cars.service.impl;

import com.example.backend_64collection.cars.dao.TypeDao;
import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.cars.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public boolean addType(TypeDto typeDto) {
        return typeDao.addType(typeDto);
    }

    @Override
    public List<TypeDto> getBrandTypeList(String brandId, String keyword) {
        return typeDao.getBrandTypeList(brandId, keyword);
    }

    @Override
    public boolean deleteType(String typeId) {
        return typeDao.deleteType(typeId);
    }
}
