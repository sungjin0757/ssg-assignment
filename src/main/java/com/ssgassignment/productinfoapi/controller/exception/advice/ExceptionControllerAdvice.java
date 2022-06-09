package com.ssgassignment.productinfoapi.controller.exception.advice;

import com.ssgassignment.productinfoapi.controller.exception.ErrorResult;
import com.ssgassignment.productinfoapi.controller.exception.ParameterErrorResult;
import com.ssgassignment.productinfoapi.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ParameterErrorResult> validateExceptions(MethodArgumentNotValidException ex){
        ParameterErrorResult error = new ParameterErrorResult("VALID_EX");
        error.addValidErrors(ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ParameterErrorResult> notValidRequestData(HttpMessageNotReadableException ex){
        ParameterErrorResult error = new ParameterErrorResult("DATA_EX");
        error.addDataErrors(ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResult> baseException(BaseException ex){
        ErrorResult error = new ErrorResult("BAD_EX", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
