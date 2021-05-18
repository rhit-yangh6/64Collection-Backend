package com.example.backend_64collection.cars.controller;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.cars.service.IBrandService;
import com.example.backend_64collection.cars.service.impl.BrandServiceImpl;
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
}