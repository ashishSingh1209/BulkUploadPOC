package com.example.bulkuploadpoc.helper;

import com.example.bulkuploadpoc.model.ImageModel;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class ImageFileValidator {
    public static String[] IMAGE_TYPE = {"image/jpeg", "image/jpg", "image/png"};
    public static double IMAGE_SIZE = 6.000f;
    public static double IMAGE_SIZE_MB = 1048576;
    public static String imageExtension;


    public static boolean hasImageFormat(MultipartFile file) {
        String[] splitter = Objects.requireNonNull(file.getContentType()).split("/");
        imageExtension = splitter[1];
        return Arrays.asList(IMAGE_TYPE).contains(Objects.requireNonNull(file.getContentType()).toLowerCase(Locale.ROOT));
    }

    public static boolean hasValidImageSize(MultipartFile file) {

        return (file.getSize() / IMAGE_SIZE_MB <= IMAGE_SIZE);
    }

    public static ImageModel imageValidated(String title, MultipartFile file) throws IOException {
        try {
            return ImageModel.builder()
                    .title(title)
                    .image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                    .imageExtension(imageExtension)
                    .build();
        } catch (IOException e) {
            throw new IOException("Error in validating Image, Error must be: " + e.getMessage());
        }

    }
}
