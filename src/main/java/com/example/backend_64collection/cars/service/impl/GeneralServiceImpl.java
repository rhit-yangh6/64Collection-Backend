package com.example.backend_64collection.cars.service.impl;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.dto.GeneralInfoDto;
import com.example.backend_64collection.cars.dto.StatsInfoDto;
import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.cars.service.IBrandService;
import com.example.backend_64collection.cars.service.IGeneralService;
import com.example.backend_64collection.cars.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneralServiceImpl implements IGeneralService {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBrandService brandService;

    private final int OTHER_BRAND_COUNT_THRESHOLD = 3;

    @Override
    public GeneralInfoDto getGeneralInfo() {
        GeneralInfoDto generalInfoDto = new GeneralInfoDto();

        List<BrandDto> brandList = brandService.getBrands("");
        generalInfoDto.setBrandsCount(brandList.size());

        List<TypeDto> typeList = typeService.getAllTypeList();

        generalInfoDto.setCarsCount(typeList.size());
        generalInfoDto.setPhotosCount(0);
        typeList.forEach((t) -> generalInfoDto.setPhotosCount(generalInfoDto.getPhotosCount() + t.getImgUrls().size()));

        return generalInfoDto;
    }

    @Override
    public StatsInfoDto getStatistics() {
        StatsInfoDto statsInfoDto = new StatsInfoDto();

        Map<String, Integer> brandsCount = new HashMap<>();
        Map<String, Integer> categoriesCount = new HashMap<>();

        brandsCount.put("Others", 0);

        List<BrandDto> brandList = brandService.getBrands("");
        brandList.forEach((b) -> {
            List<TypeDto> typeList = typeService.getBrandTypeList(b.getId(), "");
            if (typeList.size() < OTHER_BRAND_COUNT_THRESHOLD) {
                brandsCount.put("Others", brandsCount.get("Others") + typeList.size());
            } else {
                brandsCount.put(b.getName(), typeList.size());
            }
        });

        List<TypeDto> typeList = typeService.getAllTypeList();
        typeList.forEach((t) -> {
            if (categoriesCount.containsKey(t.getCategory())) {
                categoriesCount.put(t.getCategory(), categoriesCount.get(t.getCategory()) + 1);
            } else {
                categoriesCount.put(t.getCategory(), 1);
            }
        });

        statsInfoDto.setBrandCount(brandsCount);
        statsInfoDto.setCategoryCount(categoriesCount);
        return statsInfoDto;
    }
}
