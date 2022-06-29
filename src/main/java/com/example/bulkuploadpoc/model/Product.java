package com.example.bulkuploadpoc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BulkUpload")
public class Product {

    @Id
    private Integer _id;
    private String brand;
    private String color;
    private String description;
    private List<String> internalCategories;
    private String name;
    private String productType;
    private List<String> shopCategories;
    private ProductVendor productVendor;
    private String status;
    private Float weight;

}
