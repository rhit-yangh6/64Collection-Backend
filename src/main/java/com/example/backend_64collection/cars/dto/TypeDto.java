package com.example.backend_64collection.cars.dto;

import lombok.Data;

import java.util.List;

@Data
public class TypeDto {

    private String id;

    private String name;

    private String category;

    private List<String> imgUrls;

    private int make;

    private String brandId;

}
