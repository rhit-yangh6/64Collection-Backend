package com.example.backend_64collection.cars.dao;

import com.example.backend_64collection.cars.dto.BrandDto;
import com.example.backend_64collection.pojos.mongo.BrandEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean addBrand(BrandDto brandDto) {
        BrandEntity entity = new BrandEntity();
        BeanUtils.copyProperties(brandDto, entity);
        mongoTemplate.save(entity);
        return true;
    }

    public List<BrandDto> getBrands(String keyword) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("name").regex(".*" + keyword + ".*", "i"))
                .with(Sort.by(Sort.Direction.ASC, "name"));
        return mongoTemplate.find(query, BrandEntity.class).stream().map(in -> {
            BrandDto out = new BrandDto();
            BeanUtils.copyProperties(in, out);
            return out;
        }).collect(Collectors.toList());
    }

    public boolean removeBrand(String id) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, BrandEntity.class).getDeletedCount() != 0;
    }
}
