package kuit.server.common.exception;

import kuit.server.common.response.status.ResponseStatus;

public class RestaurantException extends RuntimeException{
    private final ResponseStatus exceptionStatus;

    public RestaurantException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public RestaurantException(ResponseStatus exceptionStatus, String message){
        super(message);
        this.exceptionStatus = exceptionStatus;
    }
}
