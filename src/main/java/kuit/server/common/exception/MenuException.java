package kuit.server.common.exception;

import kuit.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class MenuException extends RuntimeException {

  private final ResponseStatus exceptionStatus;

  public MenuException(ResponseStatus exceptionStatus) {
    super(exceptionStatus.getMessage());
    this.exceptionStatus = exceptionStatus;
  }

  public MenuException(ResponseStatus exceptionStatus, String message) {
    super(message);
    this.exceptionStatus = exceptionStatus;
  }
}
