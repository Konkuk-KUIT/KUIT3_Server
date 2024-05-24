package kuit.server.common.exception;

import kuit.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class StoreException extends RuntimeException{
    private final ResponseStatus exceptionStatus;

    public StoreException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
