package com.example.backend_64collection.cars.service.impl;

import com.example.backend_64collection.cars.dao.BrandDao;
import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public boolean addBrand(BrandDto brandDto) {
        return brandDao.addBrand(brandDto);
    }

    @Override
    public List<BrandDto> getBrands(String keyword) {
        return brandDao.getBrands(keyword);
    }

    @Override
    public boolean removeBrand(String brandId) {
        return brandDao.removeBrand(brandId);
    }

    @Override
    public BrandDto getSingleBrandInfo(String brandId) {
        return brandDao.getSingleBrandInfo(brandId);
    }
}
