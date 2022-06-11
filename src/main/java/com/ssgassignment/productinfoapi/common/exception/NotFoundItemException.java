package com.ssgassignment.productinfoapi.common.exception;

public class NotFoundItemException extends BaseException{
    public NotFoundItemException() {
        super();
    }

    public NotFoundItemException(String message) {
        super(message);
    }

    public NotFoundItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundItemException(Throwable cause) {
        super(cause);
    }

    protected NotFoundItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
