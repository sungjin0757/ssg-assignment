package com.ssgassignment.productinfoapi.common.exception;

import com.ssgassignment.productinfoapi.common.exception.ParameterException;
import lombok.Data;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Data
public class ParameterErrorResult {
    private String errorCode;
    private Map<String, String> errors = new HashMap<>();

    public ParameterErrorResult(String errorCode) {
        this.errorCode = errorCode;
    }

    public void addValidErrors(MethodArgumentNotValidException ex){
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
    }

    public void addDataErrors(HttpMessageNotReadableException ex){
        errors.put("Data Exception", ex.getMessage());
    }

    public void addParamErrors(ParameterException ex){
        ex.getBindingResult().getFieldErrors()
                .forEach(c -> errors.put(c.getField(), c.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(c -> errors.put(c.getCode(), c.getDefaultMessage()));
    }

}
