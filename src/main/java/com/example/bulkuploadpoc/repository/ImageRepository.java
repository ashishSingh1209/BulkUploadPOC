package com.example.bulkuploadpoc.repository;

import com.example.bulkuploadpoc.model.ImageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<ImageModel, String> {


}
