package com.minhan.productsmanagement.model.error;

import com.minhan.productsmanagement.model.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class Error {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleBindException(BindException e) {
        return ResponseObject.builder().success(false).message(e.getFieldError().getDefaultMessage())
                .data(null).build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return ResponseObject.builder().success(false).message(e.getLocalizedMessage())
                .data(null).build();
    }

    @ExceptionHandler(ExceptionObject.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject handleExceptionObject(ExceptionObject e) {
        return ResponseObject.builder().success(false).message(e.message).data(null).build();
    }
}
