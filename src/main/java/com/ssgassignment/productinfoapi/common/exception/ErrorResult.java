package com.ssgassignment.productinfoapi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String errorCode;
    private String message;
}
