package com.yeji.couponservice.common;

import com.yeji.couponservice.common.ApiUtil.ApiResponse;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    private ApiResponse<?> exception(Exception e) {
        return ApiUtil.error(500, e, e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResponse<?> notfoundException(Exception e) {
        return ApiUtil.error(404, e, e.getMessage());
    }

    
}
