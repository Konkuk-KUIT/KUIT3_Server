package kuit.server.common.exception;

import kuit.server.common.response.status.ResponseStatus;
import lombok.Getter;

import java.sql.SQLException;

@Getter
public class DatabaseException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public DatabaseException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
