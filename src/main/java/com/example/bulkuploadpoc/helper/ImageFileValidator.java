package com.example.bulkuploadpoc.helper;

import com.example.bulkuploadpoc.dao.ImageService;
import com.example.bulkuploadpoc.model.ImageModel;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class ImageFileValidator {
    public static String[] IMAGE_TYPE = {"image/jpeg", "image/jpg"};


    public static boolean hasImageFormat(MultipartFile file) {
        return Arrays.asList(IMAGE_TYPE).contains(Objects.requireNonNull(file.getContentType()).toLowerCase(Locale.ROOT));
    }

    public static ImageModel imageValidated(String title, MultipartFile file) throws IOException {
        try {
            return ImageModel.builder()
                    .title(title)
                    .image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                    .build();
        } catch (IOException e) {
            throw new IOException("Error in validating Image, Error must be: " + e.getMessage());
        }

    }
}
