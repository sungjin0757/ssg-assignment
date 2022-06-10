package com.ssgassignment.productinfoapi.controller.exception.advice;

import com.ssgassignment.productinfoapi.controller.exception.ErrorResult;
import com.ssgassignment.productinfoapi.controller.exception.ParameterErrorResult;
import com.ssgassignment.productinfoapi.exception.BaseException;
import com.ssgassignment.productinfoapi.exception.ParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

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

    @ExceptionHandler(ParameterException.class)
    public ResponseEntity<ParameterErrorResult> notValidateParam(ParameterException ex){
        ParameterErrorResult error = new ParameterErrorResult("VALID_EX");
        error.addParamErrors(ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
