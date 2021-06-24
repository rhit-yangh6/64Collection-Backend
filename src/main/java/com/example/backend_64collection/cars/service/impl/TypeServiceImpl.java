package com.example.backend_64collection.cars.service.impl;

import com.example.backend_64collection.cars.dao.TypeDao;
import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.dto.BrandTypeDto;
import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.cars.service.IBrandService;
import com.example.backend_64collection.cars.service.ITypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private IBrandService brandService;

    @Override
    public boolean addType(TypeDto typeDto) {
        return typeDao.addType(typeDto);
    }

    @Override
    public List<TypeDto> getBrandTypeList(String brandId, String keyword) {
        return typeDao.getBrandTypeList(brandId, keyword);
    }

    @Override
    public List<TypeDto> getCategoryTypeList(String category) {
        return typeDao.getCategoryTypeList(category);
    }

    @Override
    public List<TypeDto> getAllTypeList() {
        return typeDao.getAllTypeList();
    }

    @Override
    public List<BrandTypeDto> getFeaturedTypeList(int count) {
        List<TypeDto> typeList = typeDao.getFeaturedTypeList(count);
        List<BrandTypeDto> brandTypeDtoList = new ArrayList<>();
        typeList.forEach((t) -> {
            BrandTypeDto out = new BrandTypeDto();
            BrandDto brandDto = brandService.getSingleBrandInfo(t.getBrandId());
            BeanUtils.copyProperties(brandDto, out);
            BeanUtils.copyProperties(t, out);

            out.setBrandName(brandDto.getName());
            out.setTypeId(t.getId());
            out.setTypeName(t.getName());
            brandTypeDtoList.add(out);
        });
        return brandTypeDtoList;
    }

    @Override
    public TypeDto getRandomType() {
        return typeDao.getRandomType();
    }

    @Override
    public boolean deleteType(String typeId) {
        return typeDao.deleteType(typeId);
    }

    @Override
    public void increaseViewTimes(String typeId) {
        typeDao.increaseViewTimes(typeId);
    }

    @Override
    public TypeDto getSingleType(String typeId) {
        return typeDao.getSingleType(typeId);
    }
}
