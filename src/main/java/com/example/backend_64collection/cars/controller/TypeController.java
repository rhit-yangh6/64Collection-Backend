package com.example.backend_64collection.cars.controller;

import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.cars.service.ITypeService;
import com.example.backend_64collection.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/type")
@RestController
public class TypeController {

    @Autowired
    private ITypeService typeService;

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

    @GetMapping("/delete")
    public Result<?> deleteType(@RequestParam String typeId) {
        if(!typeService.deleteType(typeId)){
            return Result.fail("Failed to delete type.");
        }
        return Result.success();
    }

}
