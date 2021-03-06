package com.example.backend_64collection.cars.dao;

import com.example.backend_64collection.cars.dto.TypeDto;
import com.example.backend_64collection.pojos.mongo.TypeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class TypeDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean addType(TypeDto typeDto) {
        TypeEntity entity = new TypeEntity();
        BeanUtils.copyProperties(typeDto, entity);
        mongoTemplate.save(entity);
        return true;
    }

    public List<TypeDto> getBrandTypeList(String brandId, String keyword) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("name").regex(keyword, "i"))
                .addCriteria(Criteria.where("brandId").is(brandId))
                .with(Sort.by(Sort.Direction.ASC, "name"));
        return mongoTemplate.find(query, TypeEntity.class).stream().map(in -> {
            TypeDto out = new TypeDto();
            BeanUtils.copyProperties(in, out);
            return out;
        }).collect(Collectors.toList());
    }

    public List<TypeDto> getCategoryTypeList(String category) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("category").is(category))
                .with(Sort.by(Sort.Direction.ASC, "name"));
        return mongoTemplate.find(query, TypeEntity.class).stream().map(in -> {
            TypeDto out = new TypeDto();
            BeanUtils.copyProperties(in, out);
            return out;
        }).collect(Collectors.toList());
    }

    public TypeDto getRandomType() {
        Query query = new Query();
        query
                .with(Sort.by(Sort.Direction.ASC, "name"));
        List<TypeDto> typeList = mongoTemplate.find(query, TypeEntity.class).stream().map(in -> {
            TypeDto out = new TypeDto();
            BeanUtils.copyProperties(in, out);
            return out;
        }).collect(Collectors.toList());
        Random rand = new Random();
        return typeList.get(rand.nextInt(typeList.size()));
    }

    public boolean deleteType(String typeId) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("id").is(typeId));
        return mongoTemplate.remove(query, TypeEntity.class).getDeletedCount() != 0;
    }

    public void increaseViewTimes(String typeId) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("id").is(typeId));
        Update update = new Update();
        update
                .inc("viewTimes");
        mongoTemplate.findAndModify(query, update, TypeEntity.class);
    }

    public TypeDto getSingleType(String typeId) {
        Query query = new Query();
        query
                .addCriteria(Criteria.where("id").is(typeId));
        TypeEntity typeEntity = mongoTemplate.findOne(query, TypeEntity.class);
        if (typeEntity == null) {
            return null;
        }
        TypeDto out = new TypeDto();
        BeanUtils.copyProperties(typeEntity, out);
        return out;
    }

    public List<TypeDto> getAllTypeList() {
        return mongoTemplate.find(new Query(), TypeEntity.class).stream().map(in -> {
            TypeDto out = new TypeDto();
            BeanUtils.copyProperties(in, out);
            return out;
        }).collect(Collectors.toList());
    }

    public List<TypeDto> getFeaturedTypeList(int count) {
        Query query = new Query();
        query
                .with(Sort.by(Sort.Direction.DESC, "viewTimes"))
                .limit(count);
        return mongoTemplate.find(query, TypeEntity.class).stream().map(in -> {
            TypeDto out = new TypeDto();
            BeanUtils.copyProperties(in, out);
            return out;
        }).collect(Collectors.toList());
    }
}
