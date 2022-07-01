package com.example.bulkuploadpoc.controller;


import com.example.bulkuploadpoc.dao.ImageService;
import com.example.bulkuploadpoc.helper.ImageFileValidator;
import com.example.bulkuploadpoc.response.ResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
@RequestMapping(value = "v1/image")
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload/{title}")
    public ResponseDTO<String> uploadImage(@PathVariable String title, @RequestParam("image") MultipartFile file) {
        String message;
        if (ImageFileValidator.hasImageFormat(file)) {
            if (ImageFileValidator.hasValidImageSize(file)) {
                try {
                    imageService.uploadImage(title, file);
                    message = "Image uploaded successfully";
                    return ResponseDTO.success(message);

                } catch (Exception e) {
                    message = "Error in uploading file: Error must be: " + e.getMessage();
                    return ResponseDTO.failure(message);
                }
            }
            message = "Image is greater 6 MB.";
            return ResponseDTO.failure(message);

        }
        message = "Please uplaod Image file";
        return ResponseDTO.failure(message);
    }

    @GetMapping("/getImage/{id}")
    public String getImage(@PathVariable String id){
        return imageService.getImage(id);

    }

}
