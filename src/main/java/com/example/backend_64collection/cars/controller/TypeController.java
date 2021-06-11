package com.example.backend_64collection.cars.controller;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.dto.BrandTypeDto;
import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.cars.service.IBrandService;
import com.example.backend_64collection.cars.service.ITypeService;
import com.example.backend_64collection.common.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/type")
@RestController
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IBrandService brandService;

    @PostMapping("/add")
    public Result<?> addType(@RequestBody TypeDto typeDto) {
        if(!typeService.addType(typeDto)){
            return Result.fail("Failed to add type.");
        }
        return Result.success();
    }

    @GetMapping("/brand_list")
    public Result<?> getBrandTypeList(@RequestParam String brandId,
                                      @RequestParam String keyword) {
        return Result.success(typeService.getBrandTypeList(brandId, keyword));
    }

    @GetMapping("/category_list")
    public Result<?> getCategoryTypeList(@RequestParam String category) {
        return Result.success(typeService.getCategoryTypeList(category));
    }

    @GetMapping("/random")
    public Result<?> getRandomType() {
        return Result.success(typeService.getRandomType());
    }

    @GetMapping("/info")
    public Result<?> getTypeInfo(@RequestParam String typeId) {

        TypeDto typeDto = typeService.getSingleType(typeId);
        if (typeDto == null) {
            return Result.fail("No such type.");
        }
        typeService.increaseViewTimes(typeId);

        BrandDto brandDto = brandService.getSingleBrandInfo(typeDto.getBrandId());

        BrandTypeDto brandTypeDto = new BrandTypeDto();
        BeanUtils.copyProperties(brandDto, brandTypeDto);
        BeanUtils.copyProperties(typeDto, brandTypeDto);

        brandTypeDto.setBrandName(brandDto.getName());
        brandTypeDto.setTypeId(typeDto.getId());
        brandTypeDto.setTypeName(typeDto.getName());

        return Result.success(brandTypeDto);
    }

    @GetMapping("/delete")
    public Result<?> deleteType(@RequestParam String typeId) {
        if(!typeService.deleteType(typeId)){
            return Result.fail("Failed to delete type.");
        }
        return Result.success();
    }

}
