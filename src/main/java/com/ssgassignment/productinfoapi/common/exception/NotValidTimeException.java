package com.ssgassignment.productinfoapi.common.exception;

public class NotValidTimeException extends BaseException{
    public NotValidTimeException() {
        super();
    }

    public NotValidTimeException(String message) {
        super(message);
    }

    public NotValidTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidTimeException(Throwable cause) {
        super(cause);
    }

    protected NotValidTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
