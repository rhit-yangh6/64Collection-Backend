package com.example.backend_64collection.cars.service;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.dto.BrandVo;
import org.springframework.stereotype.Service;
import java.util.List;

public interface IBrandService {
    boolean addBrand(BrandDto brandDto);

    List<BrandVo> getBrands(String keyword);
}
