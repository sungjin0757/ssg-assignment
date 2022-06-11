package com.ssgassignment.productinfoapi.common.exception;

public class NotFoundPromotionException extends BaseException{
    public NotFoundPromotionException() {
        super();
    }

    public NotFoundPromotionException(String message) {
        super(message);
    }

    public NotFoundPromotionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPromotionException(Throwable cause) {
        super(cause);
    }

    protected NotFoundPromotionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
