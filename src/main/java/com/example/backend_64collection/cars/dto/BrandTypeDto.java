package com.example.backend_64collection.cars.dto;

import lombok.Data;

import java.util.List;

@Data
public class BrandTypeDto {
    private String brandId;

    private String brandName;

    private String iconUrl;

    private String country;

    private String typeId;

    private String typeName;

    private String category;

    private List<String> imgUrls;

    private int make;

    private String diecastBrand;

    private int viewTimes;

    private String coverImgUrl;

}
