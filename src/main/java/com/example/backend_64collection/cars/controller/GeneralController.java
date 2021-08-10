package com.example.backend_64collection.cars.controller;

import com.example.backend_64collection.cars.service.IGeneralService;
import com.example.backend_64collection.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/general")
public class GeneralController {

    @Autowired
    private IGeneralService generalService;

    @GetMapping("/info")
    public Result<?> getGeneralInfo() {
        return Result.success(generalService.getGeneralInfo());
    }

    @GetMapping("/stats")
    public Result<?> getStatistics() {
        return Result.success(generalService.getStatistics());
    }
}
