package kuit.server.common.response;

import kuit.server.common.response.status.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"code", "status", "message", "timestamp"})
public class BaseErrorResponse implements ResponseStatus {

    private final int code;
    private final int status;
    private final String message;
    private final LocalDateTime timestamp; // error발생(실패) 시각

    public BaseErrorResponse(ResponseStatus status) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public BaseErrorResponse(ResponseStatus status, String message) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
