package com.example.bulkuploadpoc.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDTO<T> {

    private boolean status;

    private String message;

    private String errorCode;

    private T data;

    private String id;

    private int httpStatusCode;

    private ResponseDTO(boolean status) {
        this.status = status;
    }

    protected ResponseDTO(boolean status, String message) {
        this(status);
        this.message = message;
    }

    protected ResponseDTO(boolean status, String message, T data) {
        this(status, message);
        this.data = data;

    }

    private ResponseDTO(boolean status, String message, T data, String id) {
        this(status, message, data);
        this.id = id;
    }

    private ResponseDTO(boolean status, String message, T data, String id, String errorCode) {
        this(status, message, data, id);
        this.errorCode = errorCode;
    }

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(true, null, data);
    }

    public static <T> ResponseDTO<T> success(String message) {
        return new ResponseDTO<>(true, message, null);

    }

    public static <T> ResponseDTO<T> success(String message, T data) {
        return new ResponseDTO<>(true, message, data);

    }

    public static <T> ResponseDTO<T> success(String message, T data, String id) {
        return new ResponseDTO<>(true, message, data, id);

    }

    public static <T> ResponseDTO<T> success(String message, T data, String id, String errorCode) {
        return new ResponseDTO<>(true, message, data, id, errorCode);

    }


    public static <T> ResponseDTO<T> failure(String message) {
        return new ResponseDTO<>(false, message);
    }

    public static <T> ResponseDTO<T> failure(String message, String id) {
        return new ResponseDTO<>(false, message, null, id);
    }

    public static <T> ResponseDTO<T> failure(String message, String id, String errorCode) {
        return new ResponseDTO<>(false, message, null, id, errorCode);
    }

    public static <T> ResponseDTO<T> failure(String message, T data) {
        return new ResponseDTO<>(false, message, data);
    }

    public static <T> ResponseDTO<T> failure(String message, T data, String id) {
        return new ResponseDTO<>(false, message, data, id);
    }

    public static <T> ResponseDTO<T> failure(String message, T data, String id, String errorCode) {
        return new ResponseDTO<>(false, message, data, id, errorCode);

    }
}
