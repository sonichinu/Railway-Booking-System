package com.irctc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiException(ApiException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiResponse;
        if(ex.getId()!=0){
             apiResponse= new ApiResponse(message, false, ex.getId());
        }
        else{
             apiResponse= new ApiResponse(message, false, ex.getField());
        }

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
