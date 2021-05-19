package com.example.backend_64collection.pojos.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "types")
@Data
public class TypeEntity {

    private String id;

    private String name;

    private String category;

    private List<String> imgUrls;

    private int make;

    private String brandId;

    private String diecastBrand;
}
