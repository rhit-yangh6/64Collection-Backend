package com.example.backend_64collection.cars.controller;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.service.IBrandService;
import com.example.backend_64collection.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @PostMapping("/add")
    public Result<?> addBrand(@RequestBody BrandDto brandDto) {
        if (brandService.addBrand(brandDto)) {
            return Result.success();
        }
        return Result.fail("Add brand failed.");
    }

    @GetMapping("/list")
    public Result<?> getBrands(@RequestParam String keyword) {
        return Result.success(brandService.getBrands(keyword));
    }

    @GetMapping("/info")
    public Result<?> getBrandInfo(@RequestParam String brandId) {
        return Result.success(brandService.getSingleBrandInfo(brandId));
    }

    @GetMapping("/delete")
    public Result<?> deleteBrand(@RequestParam String brandId) {
        if (brandService.removeBrand(brandId)) {
            return Result.success();
        }
        return Result.fail("Remove brand failed.");
    }
}
