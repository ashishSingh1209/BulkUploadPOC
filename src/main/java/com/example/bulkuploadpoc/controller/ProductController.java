package com.example.bulkuploadpoc.controller;

import com.example.bulkuploadpoc.dao.ProductService;
import com.example.bulkuploadpoc.helper.CSVHelper;
import com.example.bulkuploadpoc.model.Product;
import com.example.bulkuploadpoc.response.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
@Log4j2
public class ProductController {

    @Autowired
    private ProductService userService;

    @ApiOperation("Uploading data")
    @PostMapping("/api/upload")
    public ResponseDTO<String> uploadInBulk(@RequestParam("file") MultipartFile file) {
        String message;
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                userService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseDTO.success(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseDTO.failure(message);
            }
        }
        message = "Please upload a csv file!";
        return ResponseDTO.failure(message);
    }

    @ApiOperation("Get all the data ")
    @GetMapping("/get/data")
    public ResponseDTO<List<Product>> getAllData() {
        String message;
        try {
            List<Product> productList = userService.getAllData();
            message = "All data has been fetched";
            return ResponseDTO.success(message, productList);
        } catch (Exception e) {
            message = "Unable to fetch data error must be: " + e.getMessage();
            return ResponseDTO.failure(message);
        }
    }


    @GetMapping("/inDirectory")
    public ResponseDTO<String> getDataInDirectory() {
        try {
            return ResponseDTO.success(userService.getCsvInDirectory());
        } catch (Exception e) {
            return ResponseDTO.failure("Failed to fetch data: " + e.getMessage());
        }
    }


}
