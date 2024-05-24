package kuit.server.common.exception_handler;

import jakarta.annotation.Priority;
import kuit.server.common.exception.MenuException;
import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_MENU_STATUS;
import static kuit.server.common.response.status.BaseExceptionResponseStatus.INVALID_RESTAURANT_VALUE;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class MenuExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MenuException.class)
    public BaseErrorResponse handle_MenuException(MenuException e) {
        log.error("[handle_MenuException]", e);
        return new BaseErrorResponse(INVALID_MENU_STATUS, e.getMessage());
    }
}
