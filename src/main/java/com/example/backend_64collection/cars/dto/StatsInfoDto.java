package com.example.backend_64collection.cars.dto;

import lombok.Data;

import java.util.Map;

@Data
public class StatsInfoDto {

    private Map<String, Integer> brandCount;

    private Map<String, Integer> categoryCount;
}
