package com.ssgassignment.productinfoapi.exception;

public class DisabledUserException extends BaseException{
    public DisabledUserException() {
        super();
    }

    public DisabledUserException(String message) {
        super(message);
    }

    public DisabledUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledUserException(Throwable cause) {
        super(cause);
    }

    protected DisabledUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
