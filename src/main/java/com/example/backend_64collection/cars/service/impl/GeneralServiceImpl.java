package com.example.backend_64collection.cars.service.impl;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.dto.GeneralInfoDto;
import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.cars.service.IBrandService;
import com.example.backend_64collection.cars.service.IGeneralService;
import com.example.backend_64collection.cars.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralServiceImpl implements IGeneralService {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBrandService brandService;

    @Override
    public GeneralInfoDto getGeneralInfo() {
        GeneralInfoDto generalInfoDto = new GeneralInfoDto();

        List<BrandDto> brandList = brandService.getBrands("");
        generalInfoDto.setBrandsCount(brandList.size());

        List<TypeDto> typeList = typeService.getAllTypeList();

        generalInfoDto.setCarsCount(typeList.size());
        generalInfoDto.setPhotosCount(0);
        typeList.forEach((t) -> {
            generalInfoDto.setPhotosCount(generalInfoDto.getPhotosCount() + t.getImgUrls().size());
        });

        return generalInfoDto;
    }
}
