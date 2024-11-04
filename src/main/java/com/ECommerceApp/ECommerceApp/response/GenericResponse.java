package com.ECommerceApp.ECommerceApp.response;

import com.ECommerceApp.ECommerceApp.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@NoArgsConstructor
@Builder
//@Component
public class GenericResponse <T> {
    private int status;
    private String message;
    private T data;

    public  GenericResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public static <T> GenericResponse<T> success(T data, String message) {
        return GenericResponse.<T>builder()
                .message(message)
                .data(data)
                .status(HttpStatus.OK.value())
                .build();
    }

    public static <T> GenericResponse<T> error(String message) {
        return GenericResponse.<T>builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    public static <T> GenericResponse<T> error() {
        return GenericResponse.<T>builder()
                .message("Error!")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}