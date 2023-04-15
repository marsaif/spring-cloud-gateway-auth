package com.example.spring_app.models.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private String error;
    private T data;

    public ApiResponse(T data) {
        this.success = true;
        this.error = "";
        this.message = "";
        this.data = data;
    }
}
