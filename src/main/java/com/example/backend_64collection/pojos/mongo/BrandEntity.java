package com.example.backend_64collection.pojos.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "brands")
@Data
public class BrandEntity {

    private String id;

    private String name;

    private String country;

    private String iconUrl;

}
