package kuit.server.common.exception;

import kuit.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class ShopException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public ShopException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public ShopException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }

}
